package library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages= {"library"}, excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern="library\\..*"))
public class JavaConfig {
	@Bean
	@Scope("prototype")
	public LibraryData libraryData() {
		return new LibraryData();
	}
	
	@Bean
	@Scope("prototype")
	public UserSystem userSystem() {
		return new UserSystem();
	}
	
	@Bean
	@Scope("prototype")
	public TitleSearch titleSearch() {
		return new TitleSearch();
	}
	
	@Bean
	@Scope("prototype")
	public PublisherSearch publisherSearch() {
		return new PublisherSearch();
	}
}
