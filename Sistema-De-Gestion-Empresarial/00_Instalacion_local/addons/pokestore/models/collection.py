#Importamos 'fields' para declarar columnas y models para crear el modelo
from odoo import fields, models
#Clase de modelo: hereda de models.model, que es lo que la convierte en un modelo de Odoo

class PokeStoreCollection(models.Model):
    #Nombre tecnivo unico del modelo odoo creara la tabla de pockestore_collection
    _name= "pokestore-collection"
    #Descripcion legible del modelo la vera el usuario y los logs
    _decription = "Coleccion de figuras"
    #Orden por defecto al listar alfabetico por nombre
    _order = "name"
    
    #nombre de la coleccion: texto corto y obligatorio
    name = fields.Char(string="Nombre", required=True)
    #GENERACION pokemon de la coleccion (1,2,3,...): numero entero
    generation = fields.Integer(string="Generacion")
    #fecha de lanzamiento de la coleccion solo fecha sin hora
    release_date = fields.Date(string="Fecha de lanzamiento")
    #Activo: si lo desmarcas, la coleccion se archiva y deja de verse en las listas
    active = fields.Boolean(string="Activo",default=True)