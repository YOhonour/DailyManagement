package dailymanagement.demo.exception;

import dailymanagement.demo.utils.Status;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  异常处理
 * @date 2020/2/8 11:03
 */

public class MyException extends RuntimeException {
    private Status status;

   public MyException(Status status){
       this.status = status;
   }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
