package aspose.com;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aspose.email.Attachment;
import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SecurityOptions;
import com.aspose.email.SmtpClient;

/**
 * Servlet implementation class GenerateAndSendLetters
 */
public class SendEmailLetters extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:/uploads/";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailLetters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		// TODO Auto-generated method stub
	  String[] emails = request.getParameterValues("emails[]");
	  String[] names = request.getParameterValues("names[]");
	  String senderEmailAddress = "iamwaqastariq@gmail.com";
	  
	  for (int i=0; i < emails.length ; i++){
	    SmtpClient client = new SmtpClient("smtp.gmail.com", 587);
	    client.setSecurityOptions(SecurityOptions.Auto);
	    client.setUsername(senderEmailAddress);
	    client.setPassword( "ashjah2230");
	    MailMessage mailMessage = new MailMessage();
	    mailMessage.setFrom(new MailAddress(senderEmailAddress));
	    mailMessage.setSubject("Increment-Letter");
	    mailMessage.setBody("We are pleased to announce your Increment !");
	    mailMessage.getTo().addMailAddress(new MailAddress(emails[i]));
	    String fileName = UPLOAD_DIRECTORY + names[i].toString();
	    Attachment attachment = new Attachment(fileName + ".docx");
	    mailMessage.getAttachments().addItem(attachment);
	    client.send(mailMessage);
	  }
	  
	  request.setAttribute("message", "Emails Sent Successfully");
	  request.getRequestDispatcher("/emailSuccess.jsp").forward(request, response);
	 // System.out.println(emails.toString());
	}

}
