package com.example.ToDoApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class TodoController {
	private ToDoService todoservices;
	
	
	public TodoController(ToDoService todoservices) {
		super();
		this.todoservices = todoservices;
	}

 
	@RequestMapping("todo")
	public String listAllTodos(ModelMap model) {
	    String username = getLoggedInUsername(model);
		List<ToDo> todos = todoservices.findByUserName(username);
		model.addAttribute("todos", todos);
		return "listToDo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewToDoPage(ModelMap model) {
		 String username = getLoggedInUsername(model);
		 ToDo todo = new ToDo(0,username, "Default description" ,LocalDate.now().
				plusYears(1),false);
		model.put("todo", todo);
		return "addToDo";
	}

	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewToDoPage( ModelMap model, @Valid ToDo todo, BindingResult result) {
		
		     if(result.hasErrors()) {
		     	return "addToDo";
		    }
		   String username = getLoggedInUsername(model);
		    todoservices.addToDo(username,todo.getDescription(),
			todo.getTargetDate(),false);
			return "redirect:todo";
	}
	
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication=
		SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	@RequestMapping("delete-todo")
	public String deleteTodos(@RequestParam int id)
	{
//		delete todos
		todoservices.deleteById(id);
		return "redirect:addToDo";

	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String ShowUpdateTodos(@RequestParam int id, ModelMap model) {
	ToDo todo = todoservices.findById(id);
	model.addAttribute("todo", todo);	
		return "addToDo";
}
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateToDoPage( ModelMap model, @Valid ToDo todo, BindingResult result) {
		     
		    if(result.hasErrors()) {
		     	return "addToDo";
		    }
		    
		   String username = getLoggedInUsername(model);
		   todo.setUsername(username);
		    todoservices.updateTodo(todo);
				 
			return "redirect:todo";
	}
	
	private String getLoggedinUsername(ModelMap model) {
		  Authentication authentication = 
	SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
	
	
}
