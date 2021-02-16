package carmen.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Configs")
public class ConfigWrap {
	
	private ConfigVO con;
	
	@XmlElement(name = "Config")
	public ConfigVO getCon() {
		return con;
	}

	public void setCon(ConfigVO con) {
		this.con = con;
	}
	
}
