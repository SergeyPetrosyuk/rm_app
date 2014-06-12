package com.ita.robotamolodi;

public class Resume {
	public final String title;
	public final String description;
	public final String pubDate;
	public final String link;
	public final String site;
	public final String city;
	
	public Resume(
			String title,
			String description,
			String pubDate,
			String link,
			String site,
			String city
			) {
		this.title 		 = title;
		this.description = description;
		this.pubDate 	 = pubDate;
		this.link 		 = link;
		this.site 		 = site;
		this.city 		 = city;
	}// constructor
}// Resume
