package com.joyousasia.model.csv;


public class CustomerInputObject {
	
	
	/* Columns */
	protected String customerId;
	protected String registerDate;
	protected String status;
	protected String name;
	protected String identificationNum;
	protected String preferredNameOnCertificate1;
	protected String preferredNameOnCertificate2;
	protected String PreferredNameCalledOnStage1;
	protected String PreferredNameCalledOnStage2;
	protected String parentName;
	protected String clazz;
	protected String address;
	protected String contactNum;
	protected String email;
	protected String event;
	protected String school;
	protected String schoolBranch;
	protected String program;
	protected String regaliaRental;
	protected String regaliaDeposit;
	protected String regaliaTotal;
	protected String regaliaRental2;
	protected String regaliaDeposit2;
	protected String regaliaTotal2;
	protected String height;
	protected String weight;
	protected String headcircum;
	protected String regaliaCollectDetails;
	protected String regaliaReturnDetails;
	protected String regaliaPaymentStatus;
	protected String ticketActive;
	protected String ticketGraduate;
	protected String ticketGuestComplimentary;
	protected String ticketGuest;
	protected String ticketChildComplimentary;
	protected String ticketChild;
	protected String ticketTotal;
	protected String specialRequest;
	protected String ticketPaymentType;
	protected String ticketTransId;
	protected String ticketPaymentStatus;
	protected String activityName;
	protected String activityVenue;
	protected String activityVenueWebsite;
	protected String activityDate;
	protected String activityTime;
	protected String reportingTime;
	protected String activityTicketPrice;
	protected String activityPrice;
	protected String guestTicket;
	protected String active;
	
	/* Constructor (You can create an constructor for default value setting) */
	public CustomerInputObject(){
		
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "CustomerInputDTO ["
				+ "customerId=" + customerId + ", "
				+ "status=" + status + ", "
				+ "name=" + name + ", "
				+ "identificationNum=" + identificationNum + ", "
				+ "preferredNameOnCertificate1=" + preferredNameOnCertificate1 + ", "
				+ "preferredNameOnCertificate2=" + preferredNameOnCertificate2 + ", "
				+ "PreferredNameCalledOnStage1=" + PreferredNameCalledOnStage1 + ", "
				+ "PreferredNameCalledOnStage2=" + PreferredNameCalledOnStage2 + ", "
				+ "parentName=" + parentName + ", "
				+ "clazz=" + clazz + ", "
				+ "address=" + address + ", "
				+ "contactNum=" + contactNum + ", "
				+ "email=" + email + ", "
				+ "event=" + event + ", "
				+ "school=" + school + ", "
				+ "schoolBranch=" + schoolBranch + ", "
				+ "program=" + program + ", "
				+ "regaliaRental=" + regaliaRental + ", "
				+ "regaliaDeposit=" + regaliaDeposit + ", "
				+ "regaliaTotal=" + regaliaTotal + ", "
				+ "regaliaRental2=" + regaliaRental2 + ", "
				+ "regaliaDeposit2=" + regaliaDeposit2 + ", "
				+ "regaliaTotal2=" + regaliaTotal2 + ", "
				+ "height=" + height + ", "
				+ "weight=" + weight + ", "
				+ "headcircum=" + headcircum + ", "
				+ "regaliaCollectDetails=" + regaliaCollectDetails + ", "
				+ "regaliaReturnDetails=" + regaliaReturnDetails + ", "
				+ "regaliaPaymentStatus=" + regaliaPaymentStatus + ", "
				+ "ticketActive=" + ticketActive + ", "
				+ "ticketGraduate=" + ticketGraduate + ", "
				+ "ticketGuestComplimentary=" + ticketGuestComplimentary + ", "
				+ "ticketGuest=" + ticketGuest + ", "
				+ "ticketChildComplimentary=" + ticketChildComplimentary + ", "
				+ "ticketChild=" + ticketChild + ", "
				+ "ticketTotal=" + ticketTotal + ", "
				+ "specialRequest=" + specialRequest + ", "
				+ "ticketPaymentType=" + ticketPaymentType + ", "
				+ "ticketTransId=" + ticketTransId + ", "
				+ "ticketPaymentStatus=" + ticketPaymentStatus + ", "
				+ "activityName=" + activityName + ", "
				+ "activityVenue=" + activityVenue + ", "
				+ "activityDate=" + activityDate + ", "
				+ "activityTime=" + activityTime + ", "
				+ "reportingTime=" + reportingTime + ", "
				+ "activityTicketPrice=" + activityTicketPrice + ", "
				+ "activityPrice=" + activityPrice + ", "
				+ "guestTicket=" + guestTicket + ", "
				+ "active=" + active
				+"]";
	}

	
	/* Getter and Setter */
	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIdentificationNum() {
		return identificationNum;
	}


	public void setIdentificationNum(String identificationNum) {
		this.identificationNum = identificationNum;
	}


	public String getPreferredNameOnCertificate1() {
		return preferredNameOnCertificate1;
	}


	public void setPreferredNameOnCertificate1(String preferredNameOnCertificate1) {
		this.preferredNameOnCertificate1 = preferredNameOnCertificate1;
	}


	public String getPreferredNameOnCertificate2() {
		return preferredNameOnCertificate2;
	}


	public void setPreferredNameOnCertificate2(String preferredNameOnCertificate2) {
		this.preferredNameOnCertificate2 = preferredNameOnCertificate2;
	}


	public String getPreferredNameCalledOnStage1() {
		return PreferredNameCalledOnStage1;
	}


	public void setPreferredNameCalledOnStage1(String preferredNameCalledOnStage1) {
		PreferredNameCalledOnStage1 = preferredNameCalledOnStage1;
	}


	public String getPreferredNameCalledOnStage2() {
		return PreferredNameCalledOnStage2;
	}


	public void setPreferredNameCalledOnStage2(String preferredNameCalledOnStage2) {
		PreferredNameCalledOnStage2 = preferredNameCalledOnStage2;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public String getClazz() {
		return clazz;
	}


	public void setClazz(String clazz) {
		this.clazz = clazz;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContactNum() {
		return contactNum;
	}


	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public String getSchoolBranch() {
		return schoolBranch;
	}


	public void setSchoolBranch(String schoolBranch) {
		this.schoolBranch = schoolBranch;
	}


	public String getProgram() {
		return program;
	}


	public void setProgram(String program) {
		this.program = program;
	}


	public String getRegaliaRental() {
		return regaliaRental;
	}


	public void setRegaliaRental(String regaliaRental) {
		this.regaliaRental = regaliaRental;
	}


	public String getRegaliaDeposit() {
		return regaliaDeposit;
	}


	public void setRegaliaDeposit(String regaliaDeposit) {
		this.regaliaDeposit = regaliaDeposit;
	}


	public String getRegaliaTotal() {
		return regaliaTotal;
	}


	public void setRegaliaTotal(String regaliaTotal) {
		this.regaliaTotal = regaliaTotal;
	}


	public String getRegaliaRental2() {
		return regaliaRental2;
	}


	public void setRegaliaRental2(String regaliaRental2) {
		this.regaliaRental2 = regaliaRental2;
	}


	public String getRegaliaDeposit2() {
		return regaliaDeposit2;
	}


	public void setRegaliaDeposit2(String regaliaDeposit2) {
		this.regaliaDeposit2 = regaliaDeposit2;
	}


	public String getRegaliaTotal2() {
		return regaliaTotal2;
	}


	public void setRegaliaTotal2(String regaliaTotal2) {
		this.regaliaTotal2 = regaliaTotal2;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getHeadcircum() {
		return headcircum;
	}


	public void setHeadcircum(String headcircum) {
		this.headcircum = headcircum;
	}


	public String getRegaliaCollectDetails() {
		return regaliaCollectDetails;
	}


	public void setRegaliaCollectDetails(String regaliaCollectDetails) {
		this.regaliaCollectDetails = regaliaCollectDetails;
	}


	public String getRegaliaReturnDetails() {
		return regaliaReturnDetails;
	}


	public void setRegaliaReturnDetails(String regaliaReturnDetails) {
		this.regaliaReturnDetails = regaliaReturnDetails;
	}


	public String getRegaliaPaymentStatus() {
		return regaliaPaymentStatus;
	}


	public void setRegaliaPaymentStatus(String regaliaPaymentStatus) {
		this.regaliaPaymentStatus = regaliaPaymentStatus;
	}


	public String getTicketActive() {
		return ticketActive;
	}


	public void setTicketActive(String ticketActive) {
		this.ticketActive = ticketActive;
	}


	public String getTicketGraduate() {
		return ticketGraduate;
	}


	public void setTicketGraduate(String ticketGraduate) {
		this.ticketGraduate = ticketGraduate;
	}


	public String getTicketGuestComplimentary() {
		return ticketGuestComplimentary;
	}


	public void setTicketGuestComplimentary(String ticketGuestComplimentary) {
		this.ticketGuestComplimentary = ticketGuestComplimentary;
	}


	public String getTicketGuest() {
		return ticketGuest;
	}


	public void setTicketGuest(String ticketGuest) {
		this.ticketGuest = ticketGuest;
	}


	public String getTicketChildComplimentary() {
		return ticketChildComplimentary;
	}


	public void setTicketChildComplimentary(String ticketChildComplimentary) {
		this.ticketChildComplimentary = ticketChildComplimentary;
	}


	public String getTicketChild() {
		return ticketChild;
	}


	public void setTicketChild(String ticketChild) {
		this.ticketChild = ticketChild;
	}


	public String getTicketTotal() {
		return ticketTotal;
	}


	public void setTicketTotal(String ticketTotal) {
		this.ticketTotal = ticketTotal;
	}


	public String getSpecialRequest() {
		return specialRequest;
	}


	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}


	public String getTicketPaymentType() {
		return ticketPaymentType;
	}


	public void setTicketPaymentType(String ticketPaymentType) {
		this.ticketPaymentType = ticketPaymentType;
	}


	public String getTicketTransId() {
		return ticketTransId;
	}


	public void setTicketTransId(String ticketTransId) {
		this.ticketTransId = ticketTransId;
	}


	public String getTicketPaymentStatus() {
		return ticketPaymentStatus;
	}


	public void setTicketPaymentStatus(String ticketPaymentStatus) {
		this.ticketPaymentStatus = ticketPaymentStatus;
	}


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public String getActivityVenue() {
		return activityVenue;
	}


	public void setActivityVenue(String activityVenue) {
		this.activityVenue = activityVenue;
	}


	public String getActivityVenueWebsite() {
		return activityVenueWebsite;
	}


	public void setActivityVenueWebsite(String activityVenueWebsite) {
		this.activityVenueWebsite = activityVenueWebsite;
	}


	public String getActivityDate() {
		return activityDate;
	}


	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}


	public String getActivityTime() {
		return activityTime;
	}


	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}


	public String getReportingTime() {
		return reportingTime;
	}


	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}


	public String getActivityTicketPrice() {
		return activityTicketPrice;
	}


	public void setActivityTicketPrice(String activityTicketPrice) {
		this.activityTicketPrice = activityTicketPrice;
	}


	public String getActivityPrice() {
		return activityPrice;
	}


	public void setActivityPrice(String activityPrice) {
		this.activityPrice = activityPrice;
	}


	public String getGuestTicket() {
		return guestTicket;
	}


	public void setGuestTicket(String guestTicket) {
		this.guestTicket = guestTicket;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


}
