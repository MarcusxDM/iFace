/**
 * 
 */
package models;

/**
 * @author Vinicius
 *
 */
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Community {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private String info;
	@ManyToMany
	@JoinTable(name = "UserCommunity", joinColumns = @JoinColumn(name = "communityId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	protected List<User> members;

	@ManyToOne
	protected User owner;

		public Community() {
	}
	
	public Community(String name, String info, User owner) {
		this.name = name;
		this.info = info;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
