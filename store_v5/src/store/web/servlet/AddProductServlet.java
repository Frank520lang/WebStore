package store.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import store.domain.Product;
import store.service.ProductService;
import store.service.serviceImp.ProductServiceImp;
import store.utils.UUIDUtils;

public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProductServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            // 创建磁盘文件项工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 设置大小阈值，超出该阈值后，文件将直接写入磁盘。
            // Parameters:sizeThreshold - The size threshold, in bytes.
            // diskFileItemFactory.setSizeThreshold(3*1024*1024);

            // 创建上传核心解析类servletupload
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            // 解决上传文件中文乱码问题
            fileUpload.setHeaderEncoding("utf-8");
            // A list of FileItem instances (解析) from the request, in the order that they were transmitted.
            List<FileItem> list = fileUpload.parseRequest(request);
            Map<String, String> map = new HashMap<String, String>();
            String fileName = null;
            for (FileItem fileItem : list) {
                // 判断是否是普通的表单数据
                if (fileItem.isFormField()) {
                    // 获取到字段的名
                    String name = fileItem.getFieldName();
                    System.out.println(name);
                    // 获取到字段的值,并防止中文乱码
                    String value = fileItem.getString("utf-8");
                    System.out.println(value);
                    map.put(name, value);
                } else {
                    // 文件上传项
                    // 获取到文件名
                    fileName = fileItem.getName();
                    System.out.println(fileName);
                    // 获取到文件的输入流
                    InputStream is = fileItem.getInputStream();
                    // 获取到文件上传的路径
                    String path = "D:\\eclipse\\store_v5\\WebContent\\products\\1";
                    System.out.println(path);
                    // 获取到文件的输出流,创建一个文件输出流对象
                    OutputStream out = new FileOutputStream(path + "\\" + fileName);
                    // 将文件的输入流copy到文件的输出流中
                    IOUtils.copy(is, out);
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(out);
                }
            }
            // 创建一个product对象
            Product product = new Product();
            // 封装数据
            BeanUtils.populate(product, map);
            // 将数据写入到数据库中
            ProductService productService = new ProductServiceImp();
            product.setPid(UUIDUtils.getId());
            product.setPimage("products/1/" + fileName);
            // product.setCid(UUIDUtils.getId());
            product.setPdate(new Date());
            product.setPflag(0);
            productService.addProduct(product);
            // 转发
            response.sendRedirect(request.getContextPath() + "/AdminProductServlet?method=findAllProducts&num=1");
        } catch (FileUploadException | IllegalAccessException | InvocationTargetException | SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
