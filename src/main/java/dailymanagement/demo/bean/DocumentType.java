package dailymanagement.demo.bean;

public class DocumentType {
    private String doctype;

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype == null ? null : doctype.trim();
    }
}