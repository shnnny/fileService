package com.shnnny.fileServer.requestProcess;

import org.apache.tomcat.jni.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzh on 2018/9/21.
 */
public class Upload {

    private static final Logger log = LoggerFactory.getLogger(Upload.class);

    public static ParameterRequestWrapper uploadFile(final HttpServletRequest request, ServletResponse response, String uri) throws IOException, ServletException {
        Map threadMap = new HashMap();
        threadMap.put("IP", request.getRemoteAddr());
        threadMap.put("path", request.getRequestURI());
        ThreadParames.setMapValue(threadMap);
        FileServer fs = FileServer.instance();
        String cbPath = request.getParameter("cbPath");
        ParameterRequestWrapper wrapRequest = null;
        log.debug("the FileServer had initialed");

        /** 文件处理过程中收集参数的临时容器 */
        final Map<String, Object> paramMap = new HashMap<String, Object>();

        // 处理多个文件，不执行任何业务操作
        BuzInnerFileOperateIfc buz = new BuzInnerFileOperateIfc() {

            // 解析附件表单时，对表单其他项的过滤处理器内部实现
            public int inLoopParseFormField(FileItem fileItem) {

                String strKey = fileItem.getFieldName();

                // 过滤非法的属性名称
                if (strKey.matches("^[^a-zA-Z_]+[0-9a-zA-Z_]*$")) {
                    log.warn("忽略无效的类型属性");
                    return BuzInnerFileOperateIfc._STOP_CONTINUE;
                }

                String strVal = null;
                try {
                    strVal = fileItem.getString("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (paramMap.containsKey(strKey)) {
                    List<String> list = new ArrayList<String>();
                    list.add((String) paramMap.get(strKey));
                    list.add(strVal);

                    paramMap.put(strKey, list);
                    request.setAttribute(strKey, list);
                } else {
                    paramMap.put(strKey, strVal);
                    request.setAttribute(strKey, strVal);
                }

                return BuzInnerFileOperateIfc._NOTHING;
            }

            public int inLoopParseFileInfo(FileInfo fileInfo) {
                return BuzInnerFileOperateIfc._NOTHING;
            }
        };

        log.debug("normal form items map : " + paramMap);

        List<FileInfo> listFileInfo = null;
        List<Msg> msgList = null;
        try {
            // 获取解析完毕的文件信息列表
            listFileInfo = fs.getFileInfoList(request, buz);
            // 设置文件上传者
//            String tokenId = request.getParameter("tokenId");
//            Token token = getTokenByTokenId(tokenId);
//            if(!SyswareUtil.isEmpty(listFileInfo)){
//            	for (FileInfo info : listFileInfo) {
//            		info.setUploader(SyswareUtil.isEmpty(token) ? "" : token.getUserId());
//            	}
//            }

            log.debug("get parsing file info list from request is " + listFileInfo);

            // 再次传递文件数据到其他服务器或持久化到本次文件系统，并返回处理结果
            msgList = fs.writeMultipleFile(listFileInfo);

            if (!SyswareUtil.isEmpty(listFileInfo)) {
                // 把文件相关信息保存到request中，为后续表单关联做准备
                for (FileInfo fileInfo : listFileInfo) {

                    request.setAttribute(fileInfo.getFieldName(), fileInfo.getId());
                    request.setAttribute("cbPath", cbPath);

                    paramMap.put(fileInfo.getFieldName(), fileInfo.getId());
                }
            }

            wrapRequest = new ParameterRequestWrapper(request, paramMap);

            log.debug("validate results list is " + msgList);
        } catch (Exception e) {
            e.printStackTrace();
            log.equals(e);
        } finally {
            if (!CollectionUtils.isEmpty(listFileInfo)) {
                for (FileInfo info : listFileInfo) {
                    info.release();
                }
            }
        }

        // 如果处理文件后不需要后续处理
        // 将结果JSON格式化，反馈客户端
        if (uri.endsWith(FileConstant.fileUploadFlag)) {

            log.debug("no 无后续处理");

            String strMsg = null;
            int size = msgList.size();
            if (size == 1) {
                strMsg = SyswareJsonUtils.toJsonString(msgList.get(0));
            } else if (size > 1) {
                strMsg = SyswareJsonUtils.toJsonString(msgList);
            } else {
                strMsg = SyswareJsonUtils.toJsonString(new Msg(Msg.Memo._FAILED, Msg.Code._FAILED));
            }

            // 响应请求
            OutputStream os = null;
            HttpServletResponse resp =(HttpServletResponse) response;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            /*response.getWriter().println(strMsg.getBytes("UTF-8"));
            response.getWriter().flush();
            response.getWriter().close();*/
            os = response.getOutputStream();
            os.write(strMsg.getBytes("UTF-8"));

            os.flush();

            os.close();

        } else {

            log.debug("begin 开始后续处理");

            // 将上载成功的文件信息保存到Request范围
            request.setAttribute(FileConstant._CurrentFileInfo, msgList);

            // 在当前线程中也存在该结果列表
            FileContext.saveFileMsgInList(msgList);


            log.debug("step into normal J2EE Servlet context");

            // 重定向请求到文件接收的应用
            request.setAttribute(FileConstant._CurrentParamMap, paramMap);

        }
        return wrapRequest;
    }