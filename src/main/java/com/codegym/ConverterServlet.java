package com.codegym;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConverterServlet", urlPatterns = {"/convert"})
public class ConverterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Thiết lập kiểu nội dung trả về là HTML và hỗ trợ tiếng Việt UTF-8
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            try {
                // Đọc dữ liệu tỉ giá và số tiền USD từ form gửi lên
                float rate = Float.parseFloat(request.getParameter("rate"));
                float usd = Float.parseFloat(request.getParameter("usd"));
                
                // Thực hiện tính toán chuyển đổi tiền tệ
                float vnd = rate * usd;
                
                // Xuất kết quả trả về trình duyệt
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Kết quả chuyển đổi</title></head>");
                out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 100px;'>");
                out.println("<h2 style='color: #1b2a7a;'>KẾT QUẢ CHUYỂN ĐỔI</h2>");
                out.println("<p style='font-size: 18px;'>Tỉ giá: " + rate + " VND/USD</p>");
                out.println("<p style='font-size: 18px;'>Số tiền USD: $" + usd + "</p>");
                out.println("<h3 style='color: #27ae60; font-size: 24px;'>Thành tiền VNĐ: " + vnd + " VNĐ</h3>");
                out.println("<br><a href='index.jsp' style='text-decoration: none; padding: 10px 20px; background-color: #1b2a7a; color: white; border-radius: 4px;'>Quay lại</a>");
                out.println("</body>");
                out.println("</html>");
                
            } catch (NumberFormatException e) {
                out.println("<!DOCTYPE html>");
                out.println("<html><body>");
                out.println("<h2 style='color: red; text-align: center; margin-top: 100px;'>Lỗi: Vui lòng nhập số hợp lệ!</h2>");
                out.println("<div style='text-align: center;'><a href='index.jsp'>Quay lại</a></div>");
                out.println("</body></html>");
            }
        }
    }
}