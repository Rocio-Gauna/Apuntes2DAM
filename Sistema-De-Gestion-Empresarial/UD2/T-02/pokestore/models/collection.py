# Importamos las herramientas de Odoo para crear modelos y campos
from odoo import fields, models


# Clase nueva: el molde de las colecciones (Kanto, Johto, Hoenn...)
class PokeStoreCollection(models.Model):
    # Nombre técnico del modelo (nombre de la tabla en la base de datos)
    _name = "pokestore.collection"
    # Nombre en texto normal
    _description = "Colección de figuras"
    # Ordenar las colecciones por nombre
    _order = "name"

    # Nombre de la colección: texto corto y obligatorio
    name = fields.Char(string="Nombre", required=True)
    # Generación Pokémon (1, 2, 3...): número entero
    generation = fields.Integer(string="Generación")
    # Fecha de lanzamiento: Date guarda solo día/mes/año (sin hora)
    release_date = fields.Date(string="Fecha de lanzamiento")
    # Casilla activa: si la desmarcas, la colección se esconde (como una papelera)
    active = fields.Boolean(default=True)
    # One2many: la LISTA de figuras de esta colección
    # 1.º argumento: modelo donde buscar las figuras
    # 2.º argumento: el Many2one de la figura que apunta a esta colección
    # No crea columna en la base de datos: solo busca figuras con este collection_id
    figure_ids = fields.One2many("pokestore.figure", "collection_id", string="Figuras")