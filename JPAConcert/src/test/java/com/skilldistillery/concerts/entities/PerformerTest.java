package com.skilldistillery.concerts.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PerformerTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Performer performer;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAConcert");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		performer = em.find(Performer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		performer = null;
	}

	@Test
	void test_Venue_basic_mapping() {
		assertNotNull(performer);
		assertEquals("Deadmau5", performer.getName());
	}

}
