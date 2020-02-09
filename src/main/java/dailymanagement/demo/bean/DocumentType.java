package dailymanagement.demo.bean;

/**
 * 项目文件类型表
 * doctype： 文件类型
 *
 */
public class DocumentType {
    private String doctype;

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype == null ? null : doctype.trim();
    }
}