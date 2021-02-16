package carmen.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginUser")
public class MemWrap {

    private MemberVO mem;

    @XmlElement(name = "Member")
    public MemberVO getMem() {
        return mem;
    }

    public void setMem(MemberVO mem) {
        this.mem = mem;
    }
}