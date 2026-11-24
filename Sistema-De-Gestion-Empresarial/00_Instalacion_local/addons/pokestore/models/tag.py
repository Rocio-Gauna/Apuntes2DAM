from odoo import fields, models


class PokeStoreTag(models.Model):
    _name = "pokestore.tag"
    _description = "Etiqueta PokeStore"
    _order = "name"

    name = fields.Char(string="Nombre", required=True)
