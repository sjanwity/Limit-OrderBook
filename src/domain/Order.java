package domain;

public class Order {

	private int uniqueID;
	private int side; // 1 is buy, 0 is sell
	private int price;
	private int quantity;
	private long timestamp;
	
	public long getTimestamp() {
		return timestamp;
	}
	public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	public int getSide() {
		return side;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Order [uniqueID=" + uniqueID + ", side=" + side + ", price=" + price + ", quantity=" + quantity
				+ ", timestamp=" + timestamp + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + quantity;
		result = prime * result + side;
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		result = prime * result + uniqueID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (price != other.price)
			return false;
		if (quantity != other.quantity)
			return false;
		if (side != other.side)
			return false;
		if (uniqueID != other.uniqueID)
			return false;
		return true;
	}
	public Order(int uniqueID, int side, int quantity, int price) {
		this.uniqueID = uniqueID;
		this.side = side;
		this.price = price;
		this.quantity = quantity;
		this.timestamp = System.currentTimeMillis();
	}
	
	
}
