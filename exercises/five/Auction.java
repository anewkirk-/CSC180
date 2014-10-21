package exercises.five;
import java.util.HashMap;
import java.util.Map;

public class Auction {

	private final Integer id;
	private Map<String, Object> properties;
	private double currentBid;
	private String owner;
	private String name;
	private String description;

	public Auction(Integer id, String name, double currentBid) {
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = "";
		this.properties = new HashMap<String, Object>();
	}
	
	public Auction(Integer id, String name, String description, double currentBid) {
		this(id, name, currentBid);
		this.description = description;
	}

	public Auction(Integer id, String name, double currentBid,
			Map<String, Object> properties) {
		this(id, name, currentBid);
		this.properties = properties;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Auction) obj).id == id;
	}

	@Override
	public int hashCode() {
		return new Integer(id).hashCode();
	}

	@Override
	public String toString() {
		if (owner != null) {
			return "[" + id + " | " + name + " | " + currentBid + " | " + owner + "]";
		}
		return "[" + id + " | " + name + " | " + currentBid;
	}

	public Integer getId() {
		return id;
	}

	public double getCurrentBid() {
		return currentBid;
	}

	public Object getProperty(String propertyName) {
		if(properties.containsKey(propertyName)) {
			return properties.get(propertyName);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProperty(String propertyName, Class<T> propertyType) {
		if(properties.containsKey(propertyName)) {
			return (T) properties.get(propertyName);	
		}
		return null;
	}

	public String getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Map<String, Object> getProperties() {
		return this.properties;
	}

	public void setName(String name) {
		if (this.owner != null) {
			this.name = name;
		}
	}

	public void setProperty(String propertyName, Object value) {
		properties.put(propertyName, value);
	}

	public void setCurrentBid(double bid) {
		this.currentBid = bid;
	}

	public void setOwner(String username) {
		this.owner = username;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
