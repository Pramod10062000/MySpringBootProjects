package com.Blog.App.services.implm;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.App.Entites.Catogories;
import com.Blog.App.Exceptions.ResourceNotFoundException;
import com.Blog.App.Payloads.CategoryDto;
import com.Blog.App.repositories.CategoryRepository;
import com.Blog.App.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository catRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto cat) {
	
		Catogories catSaved=this.modelMapper.map(cat, Catogories.class);
		Catogories addedCat=catRepository.save(catSaved);
		CategoryDto categ = this.modelMapper.map(addedCat, CategoryDto.class);
		return categ ;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Catogories cat=this.catRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		Catogories updatedCatogories=this.catRepository.save(cat);
		return this.modelMapper.map(updatedCatogories, CategoryDto.class);
	}

	@Override
	public void DeleteCategory(Integer id) {
		Catogories c= this.catRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "CategoryId", id));
		this.catRepository.delete(c);
	}

	@Override
	public CategoryDto get(Integer id) {
		Catogories c= this.catRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "CategoryId", id));
		return this.modelMapper.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> GetAllCategory() {
		List<Catogories> c= this.catRepository.findAll();
		System.out.println("Inisde catService Impl");
		System.out.println(c);
		List<CategoryDto> catDto=c.stream().map((e)->this.modelMapper.map(e, CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}

}
