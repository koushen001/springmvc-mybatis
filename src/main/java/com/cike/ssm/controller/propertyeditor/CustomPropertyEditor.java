package com.cike.ssm.controller.propertyeditor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

/**
 * 自定义属性编辑器
 * 
 * @author CIKE
 *
 */
public class CustomPropertyEditor implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
	}

}
