package enums;

/**
 * Enumerados para Roles del Staff.
 *
 * Define los posibles roles que puede tener un usuario del staff de la tienda,
 * de esta manera se podra controlar que es lo que puede tocar cada unos de
 * ellos.
 *
 * @author Eduardo C. Araujo
 */
public enum RolStaff {

    /**
     * ADMIN: Control total sobre todas las operaciones del back-end.
     */
    ADMIN,
    /**
     * SHOPMANAGER: Control sobre las ventas.
     */
    SHOPMANAGER,
    /**
     * ALMACEN: Control sobre la gestion de almacen.
     */
    ALMACEN;

}
