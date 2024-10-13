package com.Blog.App.Config.JwtImplementation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtHelper;	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String reqToken=request.getHeader("Authorization");
		System.out.println("Token passed in header "+reqToken);
		String userName=null;
		String token=null;
		if(reqToken!=null && reqToken.startsWith("Bearer")) {
				token=reqToken.substring(7);
				try {
					userName=this.jwtHelper.getUsernameFromToken(token);
					System.out.println(userName);
				}catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (ExpiredJwtException e) {
					System.out.println(e.getStackTrace());
				}
				catch (MalformedJwtException e) {
					System.out.println(e.getMessage());
				}
		}else{
			System.out.println("Token syntax not followed- Bearer------");
		}
		// once we get the token , now validate
		if(userName!=null &&  SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
			
			if(this.jwtHelper.validateToken(token,userDetails)) {
				// shi chal rha hai
				// authentication karna hai
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("JWT token is invalid");
			}
		}
		else {
			System.out.println("username is null or context is not null");
		}
		filterChain.doFilter(request, response);
	}
}
