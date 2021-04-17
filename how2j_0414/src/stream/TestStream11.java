package stream;

import java.io.*;

public class TestStream11 {
    public static void main(String[] args) {

    }
   /*
    *//**
     * 	一级标签批量添加(上传 *.txt 文件的方式)
     *//*
    public class UploadLabelServlet extends HttpServlet {

        *//**
         *
         *//*
        private static final long serialVersionUID = -4129735769163441128L;

        *//**
         * 日志工厂
         *//*
        private static final DebugLog log = LogFactory.getDebugLog("CONTENTTAG");

        *//**
         * Constructor of the object.
         *//*
        public UploadLabelServlet() {
            super();
        }

        *//**
         * Servlet配置对象
         *//*
        private ServletConfig config;

        *//**
         * 销毁
         * Destruction of the servlet. <br>
         *//*
        public void destroy() {
            super.destroy(); // Just puts "destroy" string in log
            // Put your code here
        }

        *//**
         * Initialization of the servlet. <br>
         *
         * @throws ServletException if an error occurs
         *//*
        public void init(ServletConfig configs) throws ServletException {
            this.config = configs;
        }

        *//**
         * Servlet的POST处理方法
         * @param request HTTP请求对象
         * @param response HTTP请求响应对象
         * @throws ServletException
         * @throws IOException
         *//*
        protected void service(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            //设置返回消息
            String msg = "message";

            //管理上传对象
            SmartUpload labelUpload = new SmartUpload();

            //初始化上传对象
            labelUpload.initialize(config, request, response);

            //从request请求中获取session
            HttpSession session = request.getSession(false);
            //防止登录超时引起空指针异常
            if(null == session || "".equals(session))
            {
                //重定向到登录页面
                response.sendRedirect(request.getContextPath()+"/admin/adminlogin.action");
            }

            //创建本地临时目录
            String capLocalPath = Path.getWebRootPath() + "txtLabelNameFileTemp";
            FileHelper.createDir(capLocalPath);

            //得到session里面存放的用户信息
            AdminInfo admin = (AdminInfo) session.getAttribute(
                    Constant.SESSION_KEY_ADMIN_INFO);
            //操作员
            String operator = admin.getOperator();
            String fatherNodeId ="";
            try {

                //设置文件上传大小的限制(设置为3M)
                labelUpload.setMaxFileSize((1024*1024)*3L);

                //执行上传操作
                labelUpload.upload();

                //从页面获取父节点ID
                fatherNodeId = labelUpload.getRequest().getParameter("fatherNodeId");

                if("".equals(fatherNodeId) || null == fatherNodeId)
                {
                    request.setAttribute(msg, "父节点为空！："+fatherNodeId);
                    request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                    //跳转到上传失败页面，提示用户上传失败：父节点为空！
                    request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                    return;
                }
            }
            catch (SmartUploadException upe)
            {
                request.setAttribute(msg, "文件上传出现异常！："+upe.getMessage());
                request.setAttribute("backUrl", "admin/qeuryTagListPage.action");
                //跳转到上传失败页面，提示用户上传失败：文件上传出现异常！
                request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                return;
            }
            catch (IOException ioe)
            {
                request.setAttribute(msg, "文件读写错误！："+ioe.getMessage());
                request.setAttribute("backUrl", "admin/qeuryTagListPage.action");
                //跳转到上传失败页面，提示用户上传失败：文件读写错误！
                request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                return;
            }
            catch(RuntimeException e)
            {
                request.setAttribute(msg, "文件太大！文件不能超过3M ："+e.getMessage());
                request.setAttribute("backUrl", "admin/qeuryTagListPage.action");
                //跳转到上传失败页面，提示用户上传失败：文件上传错误！
                request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                return;
            }
            catch (Exception e)
            {
                request.setAttribute(msg, "文件上传错误！："+e.getMessage());
                request.setAttribute("backUrl", "admin/qeuryTagListPage.action");
                //跳转到上传失败页面，提示用户上传失败：文件上传错误！
                request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                return;
            }

            //检查文件上传数量是否正确，只允许用户一次上传一个txt文件
            if(labelUpload.getFiles().getCount() > 0)
            {
                for (int i = 0; i < labelUpload.getFiles().getCount(); i++)
                {
                    //获取文件
                    com.jspsmart.upload.File labelNameFile = labelUpload.getFiles().getFile(i);

                    //如果文件找不到或者文件内容为空
                    if(labelNameFile.isMissing() || labelNameFile.getSize() <= 0)
                    {
                        request.setAttribute(msg, "没有文件！或者上传的文件中没有数据！");
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：没有选择文件
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                        //continue;//跳出当前的这一次循环，进入下一次循环
                    }
                    //获取文件名称
                    String fileName = labelNameFile.getFileName().toUpperCase(Locale.getDefault());
                    //判断后缀名是不是 .txt
                    if(!fileName.endsWith(".TXT"))
                    {
                        request.setAttribute(msg, "文件后缀名不对！上传的文件必须是.txt的文本文件！");
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户：文件后缀名称不对
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    //文件存放路径
                    String filePath = capLocalPath+ "/" +getNewFileName(operator,labelNameFile.getFileName());

                    //上传文件到本地服务
                    try {
                        labelNameFile.saveAs(filePath);
                    }
                    catch (SmartUploadException upe)
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "文件上传到本地出现异常！："+upe.getMessage());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：文件上传出现异常！
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    catch (Exception e)
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "文件上传到本地错误！："+e.getMessage());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：文件上传错误！
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    //获取文件编码格式(因判断文件格式需要文件的BOM头信息，如果该文件没有BOM头信息的话，则默认它不是UTF-8格式的)
                    String codeFormat = getCharset(filePath);

                    //如果不是UTF-8格式
                    if(!"UTF-8".equals(codeFormat))
                    {
                        //返回错误页面，提示用户txt文本文件的编码格式必须是UTF-8
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "上传的TXT文本文件的编码格式必须是UTF-8 ！");
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：文件上传错误！
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    //获取配置项中配置的最大标签个数
                    String maxLabelNum = SystemConfig.getCmsValue("maxLabelNum");
                    //匹配是否正整数(0也算是正整数)
                    //java.util.regex.Pattern.matches("^\\d+$", maxLabelNum);
                    if(null == maxLabelNum || "".equals(maxLabelNum)
                            || !java.util.regex.Pattern.matches("^\\d+$", maxLabelNum)
                            || Integer.valueOf(maxLabelNum) > 10000)
                    {
                        maxLabelNum = "10000";
                    }
                    //去掉utf-8格式的文本文件的BOM头信息
                    trimBom(filePath);
                    UploadResponseEvt uploadEvt = new UploadResponseEvt();
                    try {
                        //读取文件内容，添加一级标签
                        uploadEvt = readFile(filePath,fatherNodeId,request,uploadEvt,maxLabelNum);
                    }
                    catch (FileNotFoundException fe)
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "读取文件内容错误，文件没找到！："+fe.getMessage());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：读取文件内容错误，文件没找到！
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    catch (IOException IOe)
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "读取文件内容错误！："+IOe.getMessage());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：读取文件内容错误！
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    //成功
                    if("0".equals(uploadEvt.getStatus()))
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "上传成功！总条数："+uploadEvt.getNumber()[0]+" 入库成功条数："+uploadEvt.getNumber()[1]+" 重复的条数："+(uploadEvt.getNumber()[0] - uploadEvt.getNumber()[1]));
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传成功页面,提示用户上传成功。
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadSuccess.jsp").forward(request, response);
                        return;
                    }
                    //超出最大行数
                    else if("1".equals(uploadEvt.getStatus()))
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "一次最多允许上传"+maxLabelNum+"个标签!请修改后重新上传。现总行数："+uploadEvt.getRowsErr());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：一次最多允许上传10000个标签
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    //有特殊字符
                    else if("2".equals(uploadEvt.getStatus()))
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "文件中有特殊字符！请修改后重新上传。行数："+uploadEvt.getRowsErr()+" 标签："+uploadEvt.getTagNameErr());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：文件中有特殊字符！请重新上传。
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                    else//标签名称大于100个字
                    {
                        delete(filePath);//删除文件
                        request.setAttribute(msg, "文件中有标签名称大于100字符(中文、字母、数字，都算一个字符)！请修改后重新上传。行数："+uploadEvt.getRowsErr()+" 标签："+uploadEvt.getTagNameErr());
                        request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                        //跳转到上传失败页面，提示用户上传失败：文件中有特殊字符！请重新上传。
                        request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                        return;
                    }
                }
            }
            else
            {
                request.setAttribute(msg, "没有文件！请先选择需要上传的txt文本文件。");
                request.setAttribute("backUrl", "admin/qeuryTagListPage.action?search=no&tagid="+fatherNodeId);
                //跳转到上传失败页面，提示用户上传失败：没有选择文件
                request.getRequestDispatcher("/WEB-INF/jsp/ums/admin/contenttag/uploadError.jsp").forward(request, response);
                return;
            }
        }

        *//**
         * 读取文件中的标签名称，然后入库
         * @param filePath 文件的完整路径及名称
         * @param fatherNodeId 父节点ID
         * @param request  http请求对象
         * @param uploadEvt 上传实体Bean对象
         * @param maxLabelNum 允许上传的最大条数
         * @throws FileNotFoundException,IOException
         *//*
        private UploadResponseEvt readFile(String filePath,String fatherNodeId,HttpServletRequest request,UploadResponseEvt uploadEvt,String maxLabelNum) throws FileNotFoundException,IOException {

            //得到session里面存放的用户信息
            AdminInfo admin = (AdminInfo) request.getSession().getAttribute(
                    Constant.SESSION_KEY_ADMIN_INFO);
            //操作员
            String operator = admin.getOperator();

            //定义读取文件的状态 0：成功，1：超出最大行数，2：有特殊字符，3：标签名称大于100个字
            String status = "0";

            *//**
             * 文件内容格式校验
             * 判断是否超出允许的最大行数
             *//*
            //BufferedReader maxNumbr = new BufferedReader(new FileReader(new File(filePath)));
            BufferedReader maxNumbr = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
            String lineCounts = maxNumbr.readLine();
            //记录有多少行数据
            int count = 0;
            int allCount = 0;//记录所有行数，包括空行
            while(null != lineCounts)
            {
                allCount++;
                //去掉空行之后的有效行数
                if(!lineCounts.trim().equals(""))
                {
                    count++;
                    //过滤特殊字符的方法
                    if(InvalidChar(lineCounts))
                    {
                        status = "2";//有特殊字符
                        //返回方法前关闭流
                        if (maxNumbr != null)
                        {
                            maxNumbr.close();
                        }
                        uploadEvt.setRowsErr(String.valueOf(allCount));
                        uploadEvt.setTagNameErr(lineCounts);
                        uploadEvt.setStatus(status);
                        //方法返回
                        return uploadEvt;
                        //break;//跳出当前while循环(终止当前while循环执行下一行代码)
                    }

                    //判断当前行是否大于100个字符(中文、字母、数字，都算一个字符)
                    if(lineCounts.trim().length() > 100)
                    {
                        status = "3";//标签名称大于50个字符(中文、字母、数字，都算一个字符)
                        //返回方法前关闭流
                        if (maxNumbr != null)
                        {
                            maxNumbr.close();
                        }
                        uploadEvt.setRowsErr(String.valueOf(allCount));
                        uploadEvt.setTagNameErr(lineCounts);
                        uploadEvt.setStatus(status);
                        //方法返回
                        return uploadEvt;
                    }
                }

                lineCounts = maxNumbr.readLine();
            }
            if (maxNumbr != null)
            {
                maxNumbr.close();
            }
            //超出允许的最大标签个数
            if(count > Integer.valueOf(maxLabelNum))
            {
                uploadEvt.setRowsErr(String.valueOf(allCount));
                status = "1";//超出最大行数
                uploadEvt.setStatus(status);
                return uploadEvt;
            }
            *//**
             * 读取数据入库
             *//*
            //BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
            String line = br.readLine();
            //获取service对象
            ContentTagService service = (ContentTagService) ContextHolder.getService("contentTagService");
            int a = 0;//总行数
            int b = 0;//导入成功的总行数
            while(null != line)
            {
                //去掉空行之后的有效行数
                if(!"".equals(line.trim()))
                {

                    //校验该父节点下有没有该标签名，如果没有则添加入库
                    if(!service.queryTagByName(fatherNodeId, line.trim()))
                    {
                        //获取序列
                        String labelId = service.getRESTagId();

                        //入库操作(添加一级标签)
                        boolean flag = service.batchInsertTag(fatherNodeId, line.trim(),labelId);

                        //添加标签成功，记录添加日志
                        if(flag)
                        {

                        }
                        flag = false;
                        //清空序列
                        labelId = "";
                        b++;
                    }
                    a++;
                }
                line = br.readLine();
            }
            if (br != null)
            {
                br.close();
            }
            uploadEvt.setNumber(new int[]{a,b});
            uploadEvt.setStatus(status);
            return uploadEvt;
        }

        *//**
         * 获取文件的编码格式
         * @param fileName
         * @return
         * @throws IOException
         *//*
        private String getCharset(String fileName) throws IOException{

            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
            int p = (bin.read() << 8) +bin.read();

            String code = null;
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
            return code;
        }

        *//**
         * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃
         *
         * @param in
         * @return
         * @throws java.io.IOException
         *//*
        public static InputStream getInputStream(InputStream in) throws IOException {

            PushbackInputStream testin = new PushbackInputStream(in);
            int ch = testin.read();
            if (ch != 0xEF) {
                testin.unread(ch);
            } else if ((ch = testin.read()) != 0xBB) {
                testin.unread(ch);
                testin.unread(0xef);
            } else if ((ch = testin.read()) != 0xBF) {
                throw new IOException("错误的UTF-8格式文件");
            } else {
                // 不需要做，这里是bom头被读完了
                // System.out.println("still exist bom");
            }
            return testin;

        }

        *//**
         * 根据一个文件名，读取完文件，干掉bom头。
         *
         * @param fileName
         * @throws java.io.IOException
         *//*
        public static void trimBom(String fileName) throws IOException {

            FileInputStream fin = new FileInputStream(fileName);
            // 开始写临时文件
            InputStream in = getInputStream(fin);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte b[] = new byte[4096];

            int len = 0;
            while (in.available() > 0) {
                len = in.read(b, 0, 4096);
                //out.write(b, 0, len);
                bos.write(b, 0, len);
            }

            in.close();
            fin.close();
            bos.close();

            //临时文件写完，开始将临时文件写回本文件。
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(bos.toByteArray());
            out.close();
        }

        *//**
         * 利用系统的当前时间+原来上传的标签文件名创建一个唯一的临时文件名
         *//*
        private String getNewFileName(String code, String oldFileName)

        {
            // 打印方法入口日志
            log.info(this.getClass().getName() + "method getNewFileName() start");

            //获取文件后缀名称
            String ext = oldFileName.substring(oldFileName.lastIndexOf("."),
                    oldFileName.length());
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            // 构造临时铃音文件名:
            String tempfileName = code + df.format(new Date()) + ext;

            // 打印方法出口日志
            log.info(this.getClass().getName() + "method getNewFileName() end");

            return tempfileName;
        }

        *//**
         * 删除上传的文件
         *
         * @param file
         *//*
        private void delete(String file)
        {
            // 打印方法入口日志
            log.info(this.getClass().getName() + "method delete() start");

            File f = new File(file);

            if (f.exists())
            {
                f.delete();
            }

            // 打印方法出口日志
            log.info(this.getClass().getName() + "method delete() end");

        }

        *//**
         * 过滤特殊字符
         * @param keyWord 需要判断是否有特殊字符的字符串
         * @return true:有特殊字符串；false:没有特殊字符串
         *//*
        public static boolean InvalidChar(String keyWord) {
            if(keyWord.trim().length() > 0)
            {
                for (int i = 0; i < keyWord.length(); i++)
                {
                    int c = keyWord.charAt(i);

				*//*if (c == '`' || c == '~' || c == '!' || c == '@' || c == '#' || c == '$' || c == '^'
	                  || c == '&' || c == '*'|| c == ')'|| c == '(' || c == '{' || c == '}' || c == '/'
	                      || c == '[' || c == ']' || c == ':' || c == '”' || c == ';' || c == '’'|| c == '<'
	                             || c == '>'|| c == '?'|| c == ','|| c == '.'|| c == '\\' || c == '"'||c == '%' || c == '|')
				{
					return true;
				}*//*
                    if (c == '<' || c == '>'|| c == '|')
                    {
                        return true;
                    }
                }
            }
            else
            {
                return true;
            }
            return false;
        }*/

    }
