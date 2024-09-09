package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha comandante Ignacio");

        entityManager.getTransaction().begin();

        Domicilio domicilio1 = Domicilio.builder()
                .nombreCalle("Belgrano")
                .numero(5000)
                .build();

        Cliente cliente1 = Cliente.builder()
                .dni(45448079L)
                .apellido("Fernandez")
                .nombre("Ignacio")
                .build();

        Factura factura1 = Factura.builder()
                .fecha("01/01/2020")
                .numero(1)
                .total(100000)
                .build();

        DetalleFactura detalle1 = DetalleFactura.builder()
                .cantidad(5)
                .subtotal(1500)
                .build();

        DetalleFactura detalle2 = DetalleFactura.builder()
                .cantidad(15)
                .subtotal(10500)
                .build();

        DetalleFactura detalle3 = DetalleFactura.builder()
                .cantidad(2)
                .subtotal(20000)
                .build();

        Articulo art1 = Articulo.builder()
                .cantidad(10)
                .denominacion("Pelota del barcelona")
                .precio(80000)
                .build();

        Articulo art2 = Articulo.builder()
                .cantidad(7)
                .denominacion("Espatula")
                .precio(50000)
                .build();

        Articulo art3 = Articulo.builder()
                .cantidad(7)
                .denominacion("Hueso de juguete")
                .precio(50000)
                .build();

        Categoria deportivo = Categoria.builder()
                .denominacion("Deportivo")
                .build();

        Categoria cocina = Categoria.builder()
                .denominacion("Cocina")
                .build();

        Categoria animales = Categoria.builder()
                .denominacion("Animales")
                .build();

        Categoria perros = Categoria.builder()
                .denominacion("Perros")
                .build();


        domicilio1.setCliente(cliente1);

        cliente1.setDomicilio(domicilio1);
        cliente1.setFacturas(Set.of(factura1));

        factura1.setCliente(cliente1);
        factura1.setDetalles(Set.of(detalle1,detalle2,detalle3));

        detalle1.setFactura(factura1);
        detalle1.setArticulo(art1);

        detalle2.setFactura(factura1);
        detalle2.setArticulo(art2);

        detalle3.setFactura(factura1);
        detalle3.setArticulo(art3);

        art1.setDetalle(Set.of(detalle1));
        art1.setCategorias(Set.of(deportivo));

        art2.setDetalle(Set.of(detalle2));
        art2.setCategorias(Set.of(cocina));

        art3.setDetalle(Set.of(detalle3));
        art3.setCategorias(Set.of(perros,animales));

//        Ejemplo de actualizacion de una tabla(debe crear todas las tablas y luego comentar el codigo que las crea)
//        Cliente cliente1 = entityManager.find(Cliente.class,1L);
//        cliente1.setNombre("ElPleke");
//        cliente1.setApellido("Phoenix");
//
//        entityManager.merge(cliente1);
//

        entityManager.persist(factura1);

        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
