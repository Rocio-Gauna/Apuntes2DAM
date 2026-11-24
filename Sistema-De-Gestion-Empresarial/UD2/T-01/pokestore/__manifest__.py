{ # nombre que se vera en la lista de aplicaciones.
    "name": "PokeStore",
    "version": "18.0.1.0.0",
    #Frase corta que describe el modulo; Sale en la tarjeta de aplicacion
    "summary": "Gestión de figuras coleccionables Pokémon",
    #Categoria donde agrupa el modulo (Sales = Ventas)
    "category": "Sales",
    
    "license": "LGPL-3",
    "depends": ["base"],
    ##Lista de archivos que Odoo tiene que leer, en este orden 
    "data": [
        # Primero los permisos. el model ya existe, por que carga primero el models/
        "security/ir.model.access.csv",
        # Despues las vistas: listas, formularios y accion
        "views/pokestore_figure_views.xml",
        "views/pokestore_menus.xml",
    ],
    #True aparece como aplicacion con su propio icono en el menu principal
    "application": True,
    #True = se puede instalar; si pones False, Odoo lo ignora
    "installable": True,
}