/**
 * 
 */
package com.treexor.springmvc.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



public class PageResource<T> extends ResourceSupport implements Page<T> {
	
	private final Page<T> page;
	
	public PageResource(Page<T> page, String pageParam,
			String sizeParam) {
		super();
		this.page = page;
		if(page.hasPreviousPage()) {
			String path = createBuilder()
				.queryParam(pageParam,page.getNumber()-1)
				.queryParam(sizeParam,page.getSize())
				.build()
				.toUriString();
			Link link = new Link(path, Link.REL_PREVIOUS);
			add(link);
		}
		if(page.hasNextPage()) {
			String path = createBuilder()
				.queryParam(pageParam,page.getNumber()+1)
				.queryParam(sizeParam,page.getSize())
				.build()
				.toUriString();
			Link link = new Link(path, Link.REL_NEXT);
			add(link);
		}
		
		Link link = buildPageLink(pageParam,0,sizeParam,page.getSize(),Link.REL_FIRST);
		add(link);
		
		int indexOfLastPage = page.getTotalPages() - 1;
		link = buildPageLink(pageParam,indexOfLastPage,sizeParam,page.getSize(),Link.REL_LAST);
		add(link);
		
		link = buildPageLink(pageParam,page.getNumber(),sizeParam,page.getSize(),Link.REL_SELF);
		add(link);
	}
	
	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}
	
	private Link buildPageLink(String pageParam,int page,String sizeParam,int size,String rel) {
		String path = createBuilder()
				.queryParam(pageParam,page)
				.queryParam(sizeParam,size)
				.build()
				.toUriString();
		Link link = new Link(path,rel);
		return link;
	}

	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalPages() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getTotalElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasPreviousPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isFirstPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasNextPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isLastPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasContent() {
		// TODO Auto-generated method stub
		return false;
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
