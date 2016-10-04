package aspose.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.email.Attachment;
import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SecurityOptions;
import com.aspose.email.SmtpClient;
import com.aspose.words.Document;

/**
 * 13 Servlet to handle File upload request from Client 14
 * 
 * @author Javin Paul 15
 */

public class FileUploadServlet extends HttpServlet {
  private final String UPLOAD_DIRECTORY = "C:/uploads/";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // process only if its multipart content
    File fileSaveDirectory = new File(UPLOAD_DIRECTORY);
    if (!fileSaveDirectory.exists()) {
      fileSaveDirectory.mkdir();
    }
    if (ServletFileUpload.isMultipartContent(request)) {
      try {
        List<FileItem> multiparts = new ServletFileUpload(
            new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : multiparts) {
          if (!item.isFormField()) {
            String name = new File(item.getName()).getName();
            item.write(new File(UPLOAD_DIRECTORY + name));
          }

        }
        // File uploaded successfully
        request.setAttribute("message", "File Uploaded Successfully");

      } catch (Exception ex) {
        request.setAttribute("message", "File Upload Failed due to " + ex);
      }
    } else {

      request.setAttribute("message",
          "Sorry this Servlet only handles file upload request");
    }
    
    ArrayList<Employee> employeeList = new ArrayList<Employee>();
    employeeList = readEmployeeFile();
    /*for(int i=0; i<employeeList.size();i++){
      System.out.println("Full Name :" + employeeList.get(i).getFullName());
      System.out.println("Email :" + employeeList.get(i).getEmail());
      System.out.println("Address :" + employeeList.get(i).getAddress());
      System.out.println("Salary :" + employeeList.get(i).getSalary());
    }*/
    generateIncrementLetters(employeeList);
    request.setAttribute("employeelist", employeeList);
    request.getRequestDispatcher("/response.jsp").forward(request, response);
    
  }

  public ArrayList<Employee> readEmployeeFile() throws FileNotFoundException {
    //String dataDir = "C:/uploads/";
    FileInputStream fstream = new FileInputStream(UPLOAD_DIRECTORY
        + "Document.xlsx");
    Workbook workbook = null;
    try {
      workbook = new Workbook(fstream);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Cells cells = workbook.getWorksheets().get(0).getCells();
    int rows = cells.getRows().getCount();
    ArrayList<Employee> employeeList = new ArrayList<Employee>();
    for (int i = 1; i < rows; i++) {
      int j = 0;
      Employee employee = new Employee();
      employee.setFullName(cells.get(i, j).getStringValue());
      employee.setEmail(cells.get(i, j + 1).getStringValue());
      employee.setAddress(cells.get(i, j + 2).getStringValue());
      employee.setSalary(Double.parseDouble(cells.get(i, j + 3)
          .getStringValue()));
      employeeList.add(employee);

    }
    return employeeList;
  }
  
  public void generateIncrementLetters(ArrayList<Employee> employeeList){
    try {
      for(int i=0; i< employeeList.size(); ){
        Document document = new Document(UPLOAD_DIRECTORY + "Mail-Merge-Template.docx");
        document.getMailMerge().execute(new String[]{"FullName","Email","Address","Salary"}, new Object[]{employeeList.get(i).getFullName(),employeeList.get(i).getEmail(),employeeList.get(i).getAddress(),employeeList.get(i).getSalary()});
        
        document.save(UPLOAD_DIRECTORY + employeeList.get(i).getFullName() + ".docx");
        i++;
      }
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  
  
  
}
