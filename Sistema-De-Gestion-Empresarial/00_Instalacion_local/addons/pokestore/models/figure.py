from odoo import api,fields, models
from odoo.exceptions import ValidationError

class PokeStoreFigure(models.Model):
    _name = "pokestore.figure"
    _description = "Figura coleccionable"
    _order = "name"
    name = fields.Char(string="Nombre", required=True)
    pokemon = fields.Char(string="Pokémon", required=True)
    collection_id = fields.Many2one(
        "pokestore.collection",
        string="Colleccion",
        ondelete="set null",
        )
    #ejercicio 3 solucion los cambios son de Tag:ids agregado 
    tag_ids = fields.Many2many(
        "pokestore.tag",
        string = "Etiquetas",
        
    )
    
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
    price = fields.Float(string="Precio", required=True)
    stock = fields.Integer(string="Stock", default=0)
    #Agregado de stock_value 
    stock_value = fields.Float(
        string = "Valor del stock",
        compute = "_compute_stock_value",
        store = True,
    )

    internal_notes = fields.Text(string="Notas internas")
    active = fields.Boolean(default=True)
    
    @api.dependes("price", "stock")
    def _compute_stock_value(self):
        for record in self:
            record.stock_value = record.price * record.stock
    
    @api.constrains("price","stock", "rarity","collection_id")
    def _check_business_rules(self):
        for record in self:
            if record.price < 0:
                raise ValidationError("El precio no puede ser negativo.")
            if record.stock < 0:
                raise ValidationError("El stock no puede ser negativo.")
            if record.rarity == "exclusive " and not record.collection_id:
                raise ValidationError("Una figura exclusiva debe tener una coleccion asignada."
                                    )
    @api.onchange(rarity)
    def _onchange_rarity(self):
        for record in self:
            if record.rarity == "exclusive" and not record.internal_notes:
                record.internal_notes = (
                "Revisar embalaje reforzado para esta figura exclusiva."
            )
    def action_deplete_stock(self):
        for record in self:
            record.stock = 0
    
    