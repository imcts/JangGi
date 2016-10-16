package frame;

import java.util.Date;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class EmailTest {
	private String mailhost = "smtp.gmail.com";
	private Session session;

	public EmailTest(String user, String pwd) {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", mailhost);
		session = Session.getInstance(props, new EmailAuthenticator(user, pwd));
	}

	public void sendMail(String subject, String body, String sender,
			String recipients) throws Exception {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(sender));
		msg.setSubject(subject);
		msg.setContent(body, "text/html;charset=EUC-KR");
		msg.setSentDate(new Date());
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
				recipients));
		Transport.send(msg);
	}

	class EmailAuthenticator extends Authenticator {
		private String id;
		private String pw;

		public EmailAuthenticator(String id, String pw) {
			super();
			this.id = id;
			this.pw = pw;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pw);
		}
	}
}

	