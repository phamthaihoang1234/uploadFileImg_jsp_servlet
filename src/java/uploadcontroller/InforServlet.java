/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadcontroller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Admin
 */
public class InforServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            String id;
            Infor infor;
            String filename = null;
            InforDAO dao = new InforDAO();
            if (action == null || action.equals("")) {
                request.setAttribute("LIST_INFOR", dao.GetAll());
                request.getRequestDispatcher("view-infor.jsp").forward(request, response);
                return;
            }
            switch (action) {
                case "AddOrEdit":
                    id = request.getParameter("id");
                    infor = dao.findById(id);
                    if (infor == null) {
                        request.setAttribute("INFOR", new Infor("", "", ""));
                    }
                    request.setAttribute("INFOR", infor);
                    request.setAttribute("ACTION", "SaveOrUpdate");
                    request.getRequestDispatcher("infor.jsp").forward(request, response);
                    break;
                case "SaveOrUpdate":
                    try {
                        // Create a factory for disk-based file items
                        DiskFileItemFactory factory = new DiskFileItemFactory();

                        // Configure a repository (to ensure a secure temp location is used)
                        ServletContext servletContext = this.getServletConfig().getServletContext();
                        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                        factory.setRepository(repository);

                        // Create a new file upload handler
                        ServletFileUpload upload = new ServletFileUpload(factory);

                        // Parse the request
                        List<FileItem> items = upload.parseRequest(request);
                        // Process the uploaded items
                        Iterator<FileItem> iter = items.iterator();
                        HashMap<String, String> fields = new HashMap<>();
                        while (iter.hasNext()) {
                            FileItem item = iter.next();

                            if (item.isFormField()) {
                                fields.put(item.getFieldName(), item.getString());
                                String fieldName = item.getFieldName();
                                String fileName = item.getName();

                            } else {
                                filename = item.getName();
                                System.out.println("filename" + filename);
                                if (filename == null || filename.equals("")) {
                                    break;

                                } else {
                                    Path path = Paths.get(filename);
                                    String storePath = servletContext.getRealPath("/uploads");
                                    File uploadFile = new File(storePath + "/" + path.getFileName());
                                    item.write(uploadFile);
                                    System.out.println(storePath + "/" + path.getFileName());
                                }
                            }
                        }
                        id = fields.get("id");
                        infor = dao.findById(id);
                        if(infor == null){
                            System.out.println("Khong tim thay");
                            Infor inforNew = new Infor();
                            inforNew.setId(fields.get("id"));
                            inforNew.setName(fields.get("name"));
                            inforNew.setPhoto(filename);
                            dao.save(inforNew);
                            System.out.println("Save");
                        }else {
                            System.out.println("Timf thay");
                            Infor inforNew = new Infor();
                            inforNew.setId(fields.get("id"));
                            inforNew.setName(fields.get("name"));
                            if(filename == null || filename.equals("")){
                                inforNew.setPhoto(infor.getPhoto());
                            }else {
                                inforNew.setPhoto(filename);
                            }
                            dao.update(inforNew);
                        }

                    } catch (Exception e) {
                    }
                    request.setAttribute("LIST_INFOR", dao.GetAll());
                   
                    request.getRequestDispatcher("view-infor.jsp").forward(request, response);
                    
                    break;

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
