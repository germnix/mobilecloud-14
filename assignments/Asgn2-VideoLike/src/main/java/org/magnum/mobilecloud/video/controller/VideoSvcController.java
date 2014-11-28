package org.magnum.mobilecloud.video.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VideoSvcController {

	@Autowired
	private VideoRepository videoRepository;
	
	@RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Iterable<Video> listVideos() {
		return videoRepository.findAll();
	}
	
	@RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody Video addVideo(@RequestBody Video aVideo) {
		return videoRepository.save(aVideo);
	}
	
	@RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH + "/{id}", method=RequestMethod.GET)
	public @ResponseBody Video getVideo(@PathVariable("id") long id) {
		return videoRepository.findOne(id);
	}
	
	@RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH + "/{id}/like", method=RequestMethod.POST)
	public void likeVideo(@PathVariable("id") long id, Principal user, HttpServletResponse response) throws IOException {
		
		Video myVideo = videoRepository.findOne(id);
		
		if(myVideo == null) {
			response.sendError(404);
			
		} else {
			if(myVideo.getUsersLiked().contains(user.getName())) {
				response.sendError(400);
			} else {
				myVideo.setLikes(myVideo.getLikes() + 1);
				myVideo.getUsersLiked().add(user.getName());
				videoRepository.save(myVideo);
				response.setStatus(200);	
			}
		}
	}
	
	@RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH + "/{id}/unlike", method=RequestMethod.POST)
	public void unlikeVideo(@PathVariable("id") long id, Principal user, HttpServletResponse response) throws IOException {
		
		Video myVideo = videoRepository.findOne(id);
		
		if(myVideo == null) {
			response.sendError(404);
			
		} else {
			if(!myVideo.getUsersLiked().contains(user.getName())) {
				response.sendError(400);
			} else {
				myVideo.setLikes(myVideo.getLikes() - 1);
				myVideo.getUsersLiked().remove(user.getName());
				videoRepository.save(myVideo);
				response.setStatus(200);	
			}
		}
	}
	
	@RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH + "/{id}/likedby", method=RequestMethod.GET)
	public @ResponseBody Collection<String> likedBy(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		
		Video myVideo = videoRepository.findOne(id);
		
		if(myVideo == null) {
			response.sendError(404);
			return null;
			
		} else {
			return myVideo.getUsersLiked();
		}
	}
	
}
