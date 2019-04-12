package lhvote.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;

@WebServlet("/captcha")
public class CaptchaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Captcha captcha = new Captcha.Builder(250, 50).addText().addBackground(new GradiatedBackgroundProducer())
				.addNoise().gimp(new DropShadowGimpyRenderer()).addBorder().build();

		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");

		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		request.getSession().setAttribute("captcha", captcha.getAnswer());
		
		CaptchaServletUtil.writeImage(response, captcha.getImage());
	}
}
