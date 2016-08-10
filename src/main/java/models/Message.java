/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

/**
 * @author Vinicius
 *
 */
@Entity
public class Message {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String contentText;
	@ManyToOne
	protected User sender;
	
	public Message(){}
	
	public Message(String contentText, User sender) {
		this.contentText = contentText;
		this.sender = sender;
	}
	
	@Override
	public String toString() {
		return ("De: "+this.getSender()+
		        "\n    ': "+ this.getContentText());
		   }
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	

}
