{
    "name": "PokeStore",
    "version": "18.0.1.0.0",
    "summary": "Gestión de figuras coleccionables Pokémon",
    "category": "Sales",
    "license": "LGPL-3",
    "depends": ["base"],
    "data": [
        "security/pokestore_security.xml",
        "security/ir.model.access.csv",
        "data/pokestore_collection_data.xml",
        "views/pokestore_collection_views.xml",
        "views/pokestore_tag_views.xml",
        "views/pokestore_figure_views.xml",
        "views/pokestore_menus.xml",
    ],
    "demo": [
        "demo/pokestore_demo.xml",
    ],
    "application": True,
    "installable": True,
}