package com.hanains.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private Long memberNo;
	private String memberName;
	private int viewCount;
	private String regDate;
	private Long grp_no;
	private Long seq_no;
	private Long lvl;
	
	
	public Long getGrp_no() {
		return grp_no;
	}
	public void setGrp_no(Long grp_no) {
		this.grp_no = grp_no;
	}
	public Long getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(Long seq_no) {
		this.seq_no = seq_no;
	}
	public Long getLvl() {
		return lvl;
	}
	public void setLvl(Long lvl) {
		this.lvl = lvl;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content="
				+ content + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", viewCount=" + viewCount + ", regDate="
				+ regDate + ", grp_no=" + grp_no + ", seq_no=" + seq_no
				+ ", lvl=" + lvl + "]";
	}
	
}
