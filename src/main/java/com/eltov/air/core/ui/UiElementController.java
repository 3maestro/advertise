package com.eltov.air.core.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/element")
public class UiElementController {

	@GetMapping("/list")
	public String getList() {
		return "../element/list";
	}
	
	@GetMapping("/modals")
	public String getModals() {
		return "../element/modals";
	}
	
	@GetMapping("/cards")
	public String getCards() {
		return "../element/cards";
	}
	
	@GetMapping("/buttons")
	public String getButtons() {
		return "../element/buttons";
	}
	
	@GetMapping("/forms")
	public String getForms() {
		return "../element/forms";
	}
	
	@GetMapping("/analytics")
	public String getAnalytics() {
		return "../element/analytics";
	}
	
	@GetMapping("/icons")
	public String getIcons() {
		return "../element/icons";
	}
	
	@GetMapping("/listgroup")
	public String getListgroup() {
		return "../element/listgroup";
	}
	
	@GetMapping("/element/datatable")
	public String getDatatable() {
		return "../element/datatable";
	}
}
