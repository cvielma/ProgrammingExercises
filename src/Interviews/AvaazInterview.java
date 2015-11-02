/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Not showing actual question for confidentiality purposes. The idea was to find separation degree based on certain given
 * classes.
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class AvaazInterview {

    public static void printSeparationDegree(final Member member) {
        List<MemberWrapper> discovered = new ArrayList<>();
        Set<Member> visited = new HashSet<>();
        int currentLevel = 0;
        List<Member> currentLevelList = new ArrayList<>();
        discovered.add(new MemberWrapper(member, currentLevel));

        while (!discovered.isEmpty()) {
            MemberWrapper current = discovered.get(0);
            discovered.remove(0);
            if (!visited.contains(current.getMember())) {
                visited.add(current.getMember());

                if (current.getCurrentLevel() > currentLevel) {
                    printLevel(currentLevelList, currentLevel);
                    currentLevelList = new ArrayList<>();
                    currentLevel++;
                }
                currentLevelList.add(current.getMember());

                for (Petition p : current.getMember().getPetitionsSigned()) {
                    for (Member m : p.getSigningMembers()) {
                        discovered.add(new MemberWrapper(m, currentLevel + 1));
                    }
                }
            }
        }
        printLevel(currentLevelList, currentLevel);

    }

    public static void printLevel(final List<Member> members, final int currentLevel) {
        if (members != null && currentLevel != 0) {
            System.out.println("\n--Members with " + currentLevel + " degrees of separation");
            for (int i = 0; i < members.size(); i++) {
                String show = members.get(i) + (i < members.size() - 1 ? "," : ".");
                System.out.print(show);
            }
        }
    }
    
    public static void main(String[] args) {
        List<Petition> petitions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            petitions.add(new Petition(i, null));
        }
        
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            members.add(new Member(i, null));
        }
        petitions.get(0).setMembers(createList(members.get(0), members.get(1), members.get(2)));
        petitions.get(1).setMembers(createList(members.get(0), members.get(3)));
        petitions.get(2).setMembers(createList(members.get(3), members.get(2), members.get(4), members.get(8)));
        petitions.get(3).setMembers(createList(members.get(1), members.get(4), members.get(5)));
        petitions.get(4).setMembers(createList(members.get(4), members.get(9), members.get(6), members.get(7)));
        
        members.get(0).setPetitions(createList(petitions.get(0), petitions.get(1)));
        members.get(1).setPetitions(createList(petitions.get(3), petitions.get(0)));
        members.get(2).setPetitions(createList(petitions.get(0), petitions.get(2)));
        members.get(3).setPetitions(createList(petitions.get(1), petitions.get(2)));
        members.get(4).setPetitions(createList(petitions.get(2), petitions.get(3), petitions.get(4)));
        members.get(5).setPetitions(createList(petitions.get(3)));
        members.get(6).setPetitions(createList(petitions.get(4)));
        members.get(7).setPetitions(createList(petitions.get(4)));
        members.get(8).setPetitions(createList(petitions.get(2)));
        members.get(9).setPetitions(createList(petitions.get(4)));
        
        printSeparationDegree(members.get(0));
    }
    
    private static <T> List<T> createList(T... items) {
        List<T> results = new ArrayList<>();
        for (T item : items) {
            results.add(item);
        }
        return results;
    }
}

class Member {

    private int id;
    private List<Petition> petitions;

    public Member(int id, List<Petition> petitions) {
        this.id = id;
        this.petitions = petitions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Petition> getPetitionsSigned() {
        return petitions;
    }

    public void setPetitions(List<Petition> petitions) {
        this.petitions = petitions;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + '}';
    }
}

class Petition {

    private int id;
    private List<Member> members;

    public Petition(int id, List<Member> members) {
        this.id = id;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Member> getSigningMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

class MemberWrapper {

    private Member member;
    private int currentLevel;

    public MemberWrapper(Member member, int currentLevel) {
        this.member = member;
        this.currentLevel = currentLevel;
    }

    public Member getMember() {
        return this.member;
    }

    public void incLevel() {
        this.currentLevel++;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }
}
