package e2e_Tugas2;

import com.tugas2.program.Model.EmployeeModel;
import com.tugas2.program.Model.loginModel;
import com.tugas2.program.Model.response_model.getEmployeeResponse;
import com.tugas2.program.Model.response_model.loginEmployee;   

public class staticVar {
    public final static String BASE_URL = "https://whitesmokehouse.com/webhook";

    public static String token;
    public static EmployeeModel employee;
    public static loginEmployee login ;
    public static getEmployeeResponse getEmployeeResponse;
}