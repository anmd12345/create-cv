package com.tdmu.controller;

import java.sql.SQLException;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tdmu.constant.CVConstant;
import com.tdmu.constant.UserConstant;
import com.tdmu.entity.CV;
import com.tdmu.entity.Experiences;
import com.tdmu.entity.Recruit;
import com.tdmu.entity.Roles;
import com.tdmu.entity.Skill;
import com.tdmu.entity.User;
import com.tdmu.service.CVService;
import com.tdmu.service.ExperiencesService;
import com.tdmu.service.RecuitService;
import com.tdmu.service.RolesService;
import com.tdmu.service.SkillService;
import com.tdmu.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private CVService cvService;

	@Autowired
	private ExperiencesService experiencesService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private RecuitService recuitService;

	@GetMapping(value = { "/", "" })
	public String doGetRedirectIndex() {
		return "redirect:/index";
	}

	Boolean checkLogin(HttpSession session) {
		User user = (User) session.getAttribute(UserConstant.CURRENT_USER);
		return ObjectUtils.isEmpty(user) ? true : false;
	}

	@GetMapping("/index")
	public String doGetIndex(Model model, HttpSession session) {
		List<CV> CVS = cvService.findAll();
		if (checkLogin(session)) {
			return "redirect:/login";
		}
		CVS.sort(new Comparator<CV>() {
			@Override
			public int compare(CV o1, CV o2) {
				return (int) (o2.getId() - o1.getId());
			}

		});
		model.addAttribute("listCV", CVS);
		List<Recruit> listRecruit = recuitService.findAll();
		listRecruit.sort(new Comparator<Recruit>() {
			@SuppressWarnings("deprecation")
			@Override
			public int compare(Recruit o1, Recruit o2) {
				if (o1.getCreatedDate().getHours() == o2.getCreatedDate().getHours()) {
					if (o1.getCreatedDate().getMinutes() == o2.getCreatedDate().getMinutes()) {
						if (o1.getCreatedDate().getSeconds() == o2.getCreatedDate().getSeconds()) {
							return 0;
						} else {
							return o2.getCreatedDate().getSeconds() - o1.getCreatedDate().getSeconds();
						}
					} else {
						return o2.getCreatedDate().getMinutes() - o1.getCreatedDate().getMinutes();
					}
				} else {
					return o2.getCreatedDate().getHours() - o1.getCreatedDate().getHours();
				}
			}

		});
		model.addAttribute("recruits", listRecruit);
		return "user/index";
	}

	@GetMapping("/myCV")
	public String doGetMyCV(Model model, HttpSession session) {
		User user = (User) session.getAttribute(UserConstant.CURRENT_USER);
		List<CV> CVS = cvService.findAllCVByUserId(user.getId());
		if (CVS.size() == 0) {
			model.addAttribute("message", "Hiện bạn không có CV nào");
		}
		if (checkLogin(session)) {
			return "redirect:/login";
		}
		model.addAttribute("listCV", CVS);
		return "user/index";
	}

	@GetMapping("/search")
	public String doGetSearch(@RequestParam String search, Model model) {
		List<CV> CVS = cvService.findBySearch(search);
		if (ObjectUtils.isEmpty(CVS)) {
			model.addAttribute("message", "Không CV ứng tuyển vị trí này");
			return "user/index";
		}
		model.addAttribute("listCV", CVS);
		return "user/index";
	}

	@GetMapping("/login")
	public String doGetLogin() {
		return "user/login";
	}
	
	@GetMapping("/index2")
	public String doGetTeamplate2() {
		return "user/index2";
	}

	@GetMapping("/register")
	public String doGetRegister(Model model, HttpSession session) {
		model.addAttribute("userRequest", new User());
		Roles role = rolesService.findById(3L);
		session.setAttribute("roleSession", role);
		return "user/register";
	}

	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/login";
	}

	@GetMapping("/details/{id}")
	public String doGetDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
		User user = (User) session.getAttribute(UserConstant.CURRENT_USER);
		if (ObjectUtils.isEmpty(user)) {
			return "redirect:/login";
		} else {
			CV cv = cvService.findById(id);
			List<Experiences> experiences = experiencesService.getAllExperiencesById(id);
			List<Skill> skills = skillService.getAllSkillById(id);
			if (ObjectUtils.isEmpty(cv)) {
				return "redirect:/index";
			}
			model.addAttribute("experiences", experiences);
			model.addAttribute("cv", cv);
			model.addAttribute("skills", skills);
			return "user/template1";
		}
	}

	@GetMapping("/admin")
	public String doGetAdmin(Model model, HttpSession session) {
		if (checkLogin(session)) {
			return "redirect:/login";
		}

		User userRequest = (User) session.getAttribute(UserConstant.CURRENT_USER);
		if (userRequest.getRoles().getId() != 1) {
			return "redirect:/index";
		}
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("userRequest", new User());
		return "admin/index";
	}

	@GetMapping("/personal")
	public String doGetPersonal(Model model, HttpSession session) {
		if (checkLogin(session)) {
			return "redirect:/login";
		}
		model.addAttribute("cvRequest", new CV());
		List<CV> listCV = cvService.findAll();
		for (CV cv : listCV) {
			session.setAttribute("currentCV" + cv.getId(), cv);
		}
		return "user/personal";
	}

	@GetMapping("/skill")
	public String doGetSkill(Model model, HttpSession session) {
		model.addAttribute("skillRequest", new Skill());
		CV cv = (CV) session.getAttribute(CVConstant.CURRENT_CV);
		List<Skill> skills = skillService.findAll(cv.getId());
		model.addAttribute("skills", skills);
		return "user/skill";
	}

	@GetMapping("/delete")
	public String doGetDelete() {
		return "user/index";
	}

	@GetMapping("/experiences")
	public String doGetWorkExperiences(Model model, HttpSession session) {
		List<CV> listCV = cvService.findAll();
		for (CV cv : listCV) {
			session.setAttribute("currentCV" + cv.getId(), cv);
		}
		model.addAttribute("exRequest", new Experiences());
		return "user/experiences";
	}

	@GetMapping("/template")
	public String doGetTemplate() {
		return "user/template";
	}

	@GetMapping("/createdRecruit")
	public String doGetCreatedRecuit() {
		return "user/createdRecruit";
	}

	@GetMapping("/recruit")
	public String doGetRecuit(Model model) {
		List<Recruit> listRecruit = recuitService.findAll();
		listRecruit.sort(new Comparator<Recruit>() {
			@SuppressWarnings("deprecation")
			@Override
			public int compare(Recruit o1, Recruit o2) {
				if (o1.getCreatedDate().getHours() == o2.getCreatedDate().getHours()) {
					if (o1.getCreatedDate().getMinutes() == o2.getCreatedDate().getMinutes()) {
						if (o1.getCreatedDate().getSeconds() == o2.getCreatedDate().getSeconds()) {
							return 0;
						} else {
							return o2.getCreatedDate().getSeconds() - o1.getCreatedDate().getSeconds();
						}
					} else {
						return o2.getCreatedDate().getMinutes() - o1.getCreatedDate().getMinutes();
					}
				} else {
					return o2.getCreatedDate().getHours() - o1.getCreatedDate().getHours();
				}
			}

		});
		model.addAttribute("recruits", listRecruit);
		return "/user/recruit";
	}

	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") User userRequest, HttpSession session)
			throws SQLException {
		User userResponse = userService.save(userRequest, session);
		if (ObjectUtils.isNotEmpty(userResponse)) {
			session.setAttribute("currentUser", userResponse);
			return "redirect:/index";
		}
		return "redirect:/register";
	}

	@PostMapping("/personal")
	public String doPostPersonal(@ModelAttribute("cvRequest") CV cv, HttpSession session) throws SQLException {
		Integer flag = 1;
		String message = "";
		List<CV> listCV = cvService.findAll();
		for (CV c : listCV) {
			if (c.getCvName().equals(cv.getCvName()) && c.getIsDeleted() == false) {
				flag = 2;
				break;
			} else if (c.getPhone().equals(cv.getPhone()) && c.getIsDeleted() == false) {
				flag = 3;
				break;
			} else if (c.getEmail().equals(cv.getEmail()) && c.getIsDeleted() == false) {
				flag = 4;
				break;
			} else if (c.getCvName() == "" && c.getIsDeleted() == false) {
				flag = 5;
				break;
			} else if (c.getPhone() == "" && c.getIsDeleted() == false) {
				flag = 6;
				break;
			} else if (c.getEmail() == "" && c.getIsDeleted() == false) {
				flag = 7;
				break;
			}
		}
		if (flag == 1) {
			CV cvResphonse = cvService.save(cv, session);
			if (ObjectUtils.isEmpty(cvResphonse)) {
				return "redirect:/personal";
			}
			session.setAttribute(CVConstant.CURRENT_CV, cvResphonse);
			return "redirect:/skill";
		} else if (flag == 2) {
			message = "Tên CV đã tồn tại, vui lòng đổi tên khác!";
		} else if (flag == 3) {
			message = "Số điện thoại đã tồn tại!";
		} else if (flag == 4) {
			message = "Email đã tồn tại!";
		} else if (flag == 5) {
			message = "Tên CV không được bỏ trống!";
		} else if (flag == 6) {
			message = "Số điện thoại không được để trống!";
		} else if (flag == 7) {
			message = "Email không được để trống!";
		}
		session.setAttribute("message", message);
		return "redirect:/personal";
	}

	@PostMapping("/skill")
	public String doPostSkill(@ModelAttribute("skillRequest") Skill skill, HttpSession session, Model model)
			throws SQLException {
		Skill skillResphonse = skillService.save(skill, session);
		if (ObjectUtils.isNotEmpty(skillResphonse)) {
			return "redirect:/skill";
		}
		return "redirect:/index";
	}

	@PostMapping("/experiences")
	public String doPostExperiences(@ModelAttribute("exRequest") Experiences experiences, HttpSession session,
			Model model) throws SQLException {
		Experiences exResphonse = experiencesService.save(experiences, session);
		if (ObjectUtils.isNotEmpty(exResphonse)) {
			return "redirect:/experiences";
		}
		return "redirect:/experiences";
	}
}
