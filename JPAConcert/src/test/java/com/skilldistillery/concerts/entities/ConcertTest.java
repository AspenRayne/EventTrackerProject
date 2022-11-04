package com.skilldistillery.concerts.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcertTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Concert concert;

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
		concert = em.find(Concert.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		concert = null;
	}

	@Test
	void test_Concert_basic_mapping() {
		assertNotNull(concert);
		assertEquals(2022, concert.getConcertDate().getYear());
	}
	
	@Test
	void test_Concert_Many_To_One_Venue_association() {
		assertNotNull(concert);
		assertEquals("Red Rocks", concert.getVenue().getName());
	}

}
