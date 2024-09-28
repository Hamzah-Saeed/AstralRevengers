public class Item
{
	// Fields
	private String name;
	private String type;
	private int quantity;
	private int price;
	
	// Constructor
	public Item(String name, String type, int quantity, int price)
	{
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}
	
	// Getters
		public String getName()
		{
			return name;
		}
		
		public String getType()
		{
			return type;
		}
		
		public int getQuantity()
		{
			return quantity;
		}
		
		public int getPrice()
		{
			return price;
		}
		
//		Setters
		public void setQuantity(int q)
		{
			this.quantity = q;
		}
}
