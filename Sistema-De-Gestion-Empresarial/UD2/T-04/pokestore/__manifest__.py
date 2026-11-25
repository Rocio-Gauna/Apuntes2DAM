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
        # Permisos primero (ahora con tres modelos)
        "security/ir.model.access.csv",
        # Vistas de colecciones y etiquetas (las figuras las necesitan para el desplegable)
        "views/pokestore_collection_views.xml",
        "views/pokestore_tag_views.xml",
        # Vistas de figuras
        "views/pokestore_figure_views.xml",
        # Menús al final: necesitan las tres acciones ya creadas
        "views/pokestore_menus.xml",
    ],
    #True aparece como aplicacion con su propio icono en el menu principal
    "application": True,
    #True = se puede instalar; si pones False, Odoo lo ignora
    "installable": True,
}