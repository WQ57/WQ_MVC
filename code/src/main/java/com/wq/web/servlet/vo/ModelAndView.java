package com.wq.web.servlet.vo;

/**
 * 模型与视图.
 * 
 * @author wuqing
 * @date 2014年8月8日 下午9:55:37
 */
public class ModelAndView {

	private Model model = new Model();

	private View view = new View();

	/**
	 * 为模型添加元素.
	 * 
	 * @param modleName
	 * @param modleValue
	 * @author wuqing
	 * @date 2014年8月8日 下午9:56:58
	 */
	public void addModle(String modleName, Object modleValue) {
		model.getBean().put(modleName, modleValue);
	}

	/**
	 * 设置视图名称.
	 * 
	 * @param viewName
	 * @author wuqing
	 * @date 2014年8月8日 下午9:57:29
	 */
	public void setViewName(String viewName) {
		view.setName(viewName);
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

}
