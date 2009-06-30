package fi.hut.soberit.agilefant.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fi.hut.soberit.agilefant.business.UserBusiness;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.Project;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.util.UserComparator;




@Component("dailyWorkAction")
@Scope("prototype")
public class DailyWorkAction extends ActionSupport {
    private static final long serialVersionUID = 5732278003634700787L;

    private List<Iteration> iterations;

    private List<Project> projects;

//    private Map<Backlog, EffortSumData> effortSums;
//
//    private Map<Backlog, EffortSumData> originalEstimates;
//
//    private Map<Backlog, List<BacklogItem>> bliMap;

    private User user;

    @Autowired
    private UserBusiness userBusiness;
    
    private int userId;

//    private List<BacklogItem> backlogItemsForUserInProgress;

    private List<User> userList;
    
    private List<User> enabledUsers = new ArrayList<User>();
    
//    private Map<Backlog, BacklogLoadData> loadDatas = new HashMap<Backlog, BacklogLoadData>();
//    
//    private DailyWorkLoadData dailyWorkLoadData = new DailyWorkLoadData();
    
    /*private Map<Integer, String> effortsLeftMap = new HashMap<Integer, String>();
    private Map<Integer, String> overheadsMap = new HashMap<Integer, String>();*/
//    private Map<Integer, AFTime> totalsMap = new HashMap<Integer, AFTime>();
    private int weeksAhead = 5;
    private List<Integer> weekNumbers;
    private String[] overallTotals;

    @Override
    public String execute() throws Exception {
        /*
         * Get the user id from session variables. This enables the Daily Work
         * page to remember the selected user.
         */
        int dailyWorkUserId = 0;
        if (ActionContext.getContext() != null
                && ActionContext.getContext().getSession() != null) {
            Object sessionUser = ActionContext.getContext().getSession().get(
                    "dailyWorkUserId");

            if (sessionUser != null) {
                dailyWorkUserId = (Integer) sessionUser;
            }
        }

        if (userId == 0) {
            if (dailyWorkUserId == 0) {
                userId = SecurityUtil.getLoggedUserId();
            } else {
                userId = dailyWorkUserId;
            }
        }

        user = userBusiness.retrieve(userId);
//        effortSums = new HashMap<Backlog, EffortSumData>();
//        originalEstimates = new HashMap<Backlog, EffortSumData>();
//
//        bliMap = userBusiness.getBacklogItemsAssignedToUser(user);
        projects = new ArrayList<Project>();
        iterations = new ArrayList<Iteration>();

//        Set<Backlog> backlogs = bliMap.keySet();
//        Iterator<Backlog> iter = backlogs.iterator();

//        while (iter.hasNext()) {
//            Backlog backlog = iter.next();
//            if (backlog instanceof Project) {
//                projects.add((Project) backlog);
//            } else if (backlog instanceof Iteration) {
//                iterations.add((Iteration) backlog);
//            }
//            List<BacklogItem> blis = bliMap.get(backlog);
//            EffortSumData effLeftSum = backlogBusiness.getEffortLeftSum(blis);
//            effortSums.put(backlog, effLeftSum);
//            EffortSumData origEstSum = backlogBusiness.getOriginalEstimateSum(blis);
//            originalEstimates.put(backlog, origEstSum);
//            
//            hourEntryBusiness.loadSumsToBacklogItems(backlog);
//        }
//
//        Collections.sort(projects, new BacklogComparator());
//        Collections.sort(iterations, new BacklogComparator());
//
//        backlogItemsForUserInProgress = userBusiness
//                .getBacklogItemsInProgress(user);

        userList = new ArrayList<User>(); 
        userList.addAll(userBusiness.retrieveAll());
        
        enabledUsers.addAll(userBusiness.getEnabledUsers());
        Collections.sort(userList, new UserComparator());
        Collections.sort(enabledUsers, new UserComparator());

//        DailyWorkLoadData data = this.projectBusiness.getDailyWorkLoadData(this.user, this.weeksAhead);
        
//        setDailyWorkLoadData(data);
//        
//        setWeekNumbers(data.getWeekNumbers());
//        
//        setTotalsMap(data.getWeeklyTotals());
//                
//        this.setLoadDatas(data.getLoadDatas());
        return super.execute();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Iteration> getIterations() {
        return iterations;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public Map<Backlog, List<BacklogItem>> getBliMap() {
//        return bliMap;
//    }
//
//    public void setBliMap(Map<Backlog, List<BacklogItem>> bliMap) {
//        this.bliMap = bliMap;
//    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserBusiness(UserBusiness userBusiness) {
        this.userBusiness = userBusiness; 
    }

//    public List<BacklogItem> getBacklogItemsForUserInProgress() {
//        return backlogItemsForUserInProgress;
//    }
//
//    public void setBacklogItemsForUserInProgress(
//            List<BacklogItem> backlogItemsForUserInProgress) {
//        this.backlogItemsForUserInProgress = backlogItemsForUserInProgress;
//    }
//
//    public Map<Backlog, EffortSumData> getEffortSums() {
//        return effortSums;
//    }
//
//    public void setEffortSums(Map<Backlog, EffortSumData> effortSums) {
//        this.effortSums = effortSums;
//    }

//    public Map<Backlog, EffortSumData> getOriginalEstimates() {
//        return originalEstimates;
//    }

    public int getWeeksAhead() {
        return weeksAhead;
    }

    public void setWeeksAhead(int weeksAhead) {
        this.weeksAhead = weeksAhead;
    }


    public List<Integer> getWeekNumbers() {
        return weekNumbers;
    }

    public void setWeekNumbers(List<Integer> weekNumbers) {
        this.weekNumbers = weekNumbers;
    }

    public String[] getOverallTotals() {
        return overallTotals;
    }

    public void setOverallTotals(String[] overallTotals) {
        this.overallTotals = overallTotals;
    }

//    public Map<Backlog, BacklogLoadData> getLoadDatas() {
//        return loadDatas;
//    }
//
//    public void setLoadDatas(Map<Backlog, BacklogLoadData> loadDatas) {
//        this.loadDatas = loadDatas;
//    }
//
//    public DailyWorkLoadData getDailyWorkLoadData() {
//        return dailyWorkLoadData;
//    }
//
//    public void setDailyWorkLoadData(DailyWorkLoadData dailyWorkLoadData) {
//        this.dailyWorkLoadData = dailyWorkLoadData;
//    }
//
//    public Map<Integer, AFTime> getTotalsMap() {
//        return totalsMap;
//    }
//
//    public void setTotalsMap(Map<Integer, AFTime> totalsMap) {
//        this.totalsMap = totalsMap;
//    }

    public List<User> getEnabledUsers() {
        return enabledUsers;
    }

    public void setEnabledUsers(List<User> enabledUsers) {
        this.enabledUsers = enabledUsers;
    }

}