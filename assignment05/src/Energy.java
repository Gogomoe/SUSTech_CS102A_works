
public enum Energy {
	ELECTRIC("(Electric only)",true,false),
	HYBRID("(Hybrid)",true,true),
	GAS("(Gas only)",false,true);
	private String desc;
	private boolean chargeable;
	private boolean oiling;
	private Energy(String desc,boolean chargeable,boolean oiling){
		this.desc=desc;
		this.chargeable=chargeable;
		this.oiling=oiling;
		
	}
	public String getDesc() {
		return this.desc;
	}
	public boolean isRecharge() {
		return this.chargeable;
	}
	public boolean isOiling() {
		return this.oiling;
	}
}
