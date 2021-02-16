package carmen.domain;

public class ConfigVO {
	private boolean idsave;
	private boolean autologin;
	public boolean getIdsave() {
		return idsave;
	}
	public void setIdsave(boolean idsave) {
		this.idsave = idsave;
	}
	public boolean getAutologin() {
		return autologin;
	}
	public void setAutologin(boolean autologin) {
		this.autologin = autologin;
	}
	public ConfigVO() {};
	public ConfigVO(boolean idsave, boolean autologin) {
		super();
		this.idsave = idsave;
		this.autologin = autologin;
	}
}
