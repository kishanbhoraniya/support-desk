package com.finalhints.reposioty.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.finalhints.entity.Category;
import com.finalhints.entity.Project;
import com.finalhints.entity.ProjectRole;
import com.finalhints.entity.Status;
import com.finalhints.entity.Ticket;
import com.finalhints.entity.User;
import com.finalhints.reposioty.TicketCustomRepository;

public class TicketRepositoryImpl implements TicketCustomRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ticket> getAllTickets(User user, List<User> createdBy, List<User> assignedTo, List<Status> status,
			List<Project> projects, Integer offset, Integer limit) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ticket> criteriaQuery = builder.createQuery(Ticket.class);
		Root<ProjectRole> project_role = criteriaQuery.from(ProjectRole.class);
		Join<ProjectRole, Project> project = project_role.join("project");
		Join<Project, Category> categories = project.join("projectsCategories");
		Join<Category, Ticket> tickets = categories.join("tickets");
		List<Predicate> conditions = new ArrayList<>();
		if (user != null) {
			conditions.add(builder.equal(project_role.get("user"), user));
		}
		if (createdBy != null && createdBy.size() > 0) {
			conditions.add(tickets.get("createdByUser").in(createdBy));
		}
		if (assignedTo != null && assignedTo.size() > 0) {
			conditions.add(tickets.get("assigneeUser").in(assignedTo));
		}
		TypedQuery<Ticket> query = em.createQuery(
				criteriaQuery.select(tickets).where(conditions.toArray(new Predicate[] {})).distinct(true));
		return query.getResultList();
	}

}
