package com.teluguSkillHub.taskproject.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teluguSkillHub.taskproject.entity.Task;
import com.teluguSkillHub.taskproject.entity.Users;
import com.teluguSkillHub.taskproject.exception.APIException;
import com.teluguSkillHub.taskproject.exception.TaskNotFound;
import com.teluguSkillHub.taskproject.exception.UserNotFound;
import com.teluguSkillHub.taskproject.payload.TaskDto;
import com.teluguSkillHub.taskproject.repository.TaskRepository;
import com.teluguSkillHub.taskproject.repository.UserRepository;
import com.teluguSkillHub.taskproject.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto saveTask(long userid, TaskDto taskDto) {
	    Users user=userRepository.findById(userid).orElseThrow(
	    		()->new UserNotFound(String.format("User Id %d not found",userid))
	    		);
		Task task=modelMapper.map(taskDto,Task.class);
		task.setUsers(user);
		//After setting the user, we are storing the data in DB
		Task savedTask=taskRepository.save(task);
		return modelMapper.map(savedTask,TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTasks(long userid) {
		 userRepository.findById(userid).orElseThrow(
	    		()->new UserNotFound(String.format("User Id %d not found",userid))
	    		);
		 List<Task>tasks=taskRepository.findAllByUsersId(userid);
		return tasks.stream().map(
				task -> modelMapper.map(task,TaskDto.class)
				).collect(Collectors.toList());
	}

	@Override
	public TaskDto getTask(long userid, long taskid) {
		Users users=userRepository.findById(userid).orElseThrow(
	    		()->new UserNotFound(String.format("User Id %d not found",userid))
	    		);
		Task task=taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task id %d not found",taskid))
				);
		if(users.getId() !=task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
		}
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(long userid, long taskid) {
		Users users=userRepository.findById(userid).orElseThrow(
	    		()->new UserNotFound(String.format("User Id %d not found",userid))
	    		);
		Task task=taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task id %d not found",taskid))
				);
		if(users.getId() !=task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
		}
		
		taskRepository.deleteById(taskid);//delete the task
			
	}

}
