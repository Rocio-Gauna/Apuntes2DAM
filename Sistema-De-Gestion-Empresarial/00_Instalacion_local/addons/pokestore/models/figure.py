# Importamos fields y models de Odoo
from odoo import fields, models


# Mismo molde de figura del Tutorial 1
class PokeStoreFigure(models.Model):
    # Nombre técnico del modelo
    _name = "pokestore.figure"
    # Nombre en texto normal
    _description = "Figura coleccionable"
    # Orden alfabético por nombre
    _order = "name"

    # Nombre de la figura (obligatorio)
    name = fields.Char(string="Nombre", required=True)
    # Pokémon que representa (obligatorio)
    pokemon = fields.Char(string="Pokémon", required=True)
    # El campo de texto antiguo "collection" YA NO ESTÁ: lo sustituye collection_id
    # Many2one: cada figura apunta a UNA colección. Odoo crea la columna collection_id en la tabla
    # 1.º argumento: modelo al que apunta (colecciones)
    collection_id = fields.Many2one("pokestore.collection", string="Colección")
    # Many2many: una figura puede tener muchas etiquetas y una etiqueta, muchas figuras
    # Odoo crea solo una tabla intermedia que guarda las parejas (figura, etiqueta)
    tag_ids = fields.Many2many("pokestore.tag", string="Etiquetas")
    # Lista desplegable de rareza, con valor guardado y texto visible
    rarity = fields.Selection(
        [
            ("normal", "Normal"),
            ("rare", "Rara"),
            ("exclusive", "Exclusiva"),
        ],
        string="Rareza",
        default="normal",
        required=True,
    )
    # Precio con decimales (obligatorio)
    price = fields.Float(string="Precio", required=True)
    # Unidades disponibles (entero, empieza en 0)
    stock = fields.Integer(string="Stock", default=0)
    # Casilla para archivar la figura sin borrarla
    active = fields.Boolean(default=True)