package Test1;

public class Student {
	private String stuname;
	private String stunumber;
	private String stuclass;

	/**
	 * @param stuname
	 * @param stunumber
	 * @param stuclass
	 */
	public Student() {
		this.stuname = "郑康林";
		this.stunumber = "17";
		this.stuclass = "D18C44";
	}

	/**
	 * @return the stuname
	 */
	public String getStuname() {
		return stuname;
	}

	/**
	 * @param stuname
	 *            the stuname to set
	 */
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	/**
	 * @return the stunumber
	 */
	public String getStunumber() {
		return stunumber;
	}

	/**
	 * @param stunumber
	 *            the stunumber to set
	 */
	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}

	/**
	 * @return the stuclass
	 */
	public String getStuclass() {
		return stuclass;
	}

	/**
	 * @param stuclass
	 *            the stuclass to set
	 */
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("我是%s班级学生，我叫%s，学号为%s", stuclass, stuname, stunumber);
	}

}
