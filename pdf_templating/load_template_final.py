import json
import fitz
from pypdf import PdfReader
import base64
import json
from io import BytesIO





def get_all_fields(pdf_path):
    reader = PdfReader(pdf_path)
    form_fields = {}
    fields = reader.get_fields()
    for field in fields:

        field_name = fields[field].get("/T")  # Field name
        field_value = fields[field].get("/V", '')  # Field value
        field_type = fields[field].get("/FT", '')  # Field type
        if field_name == "photograph":
            photo_field = fields[field]
            print(field_name, field_value, field_type)
        form_fields[field_name] = field_value if field_value else None
    return form_fields




def read_all_form_fields(pdf_path):
    # Open the PDF
    pdf_document = fitz.open(pdf_path)
    form_fields = {}

    # Iterate over pages
    for page_number, page in enumerate(pdf_document, start=1):
        for widget in page.widgets():  # Access form widgets on the page
            field = {
                "page": page_number,
                "name": widget.field_name,         # Field name
                "type": widget.field_type,         # Field type
                "value": widget.field_value,              # Current value of the field
                "bbox": widget.rect,               # Field bounding box
            }
            form_fields[widget.field_name] = widget.field_value

    pdf_document.close()
    return form_fields

"""
1 PDF_WIDGET_TYPE_BUTTON 
2 PDF_WIDGET_TYPE_CHECKBOX 
3 PDF_WIDGET_TYPE_COMBOBOX 
4 PDF_WIDGET_TYPE_LISTBOX 
5 PDF_WIDGET_TYPE_RADIOBUTTON 
6 PDF_WIDGET_TYPE_SIGNATURE 
7 PDF_WIDGET_TYPE_TEXT 
"""


def update_pdf_fields_from_json(pdf_path, output_path, fields_data):
    
    pdf_document = fitz.open(pdf_path)


    for index, page in enumerate(pdf_document):
        
        
        for field in page.widgets():
            if field.field_name in fields_data:  
                value = fields_data[field.field_name] 
                
                if "image" in field.field_name and value:
        
                    image_data = base64.b64decode(value) 
                    stream = BytesIO(image_data)


                    bbox = field.rect
                    image_xref = page.insert_image(
                        bbox,
                        keep_proportion=True,
                        stream = stream
                    )
                if field.field_type == fitz.mupdf.PDF_WIDGET_TYPE_TEXT:
                    field.field_value = str(value)  
                    field.update()
                elif field.field_type == fitz.mupdf.PDF_WIDGET_TYPE_CHECKBOX: 
                   field.field_value = str(value)
                   field.update()
                elif field.field_type == fitz.mupdf.PDF_WIDGET_TYPE_RADIOBUTTON:
                    field.set_radio_button(bool(value)) 
                elif field.field_type == fitz.mupdf.PDF_WIDGET_TYPE_COMBOBOX or field.field_type == fitz.mupdf.PDF_WIDGET_TYPE_LISTBOX:  
                    field.choice_value = str(value)  


    pdf_document.save(output_path)
    pdf_document.close()



def get_data_to_fill(file_name):
    with open(file_name) as f:
        data = json.load(f)
        return data

def save_to_json(data, json_filename):
    with open(json_filename, 'w', encoding='utf-8') as json_file:
        json.dump(data, json_file, ensure_ascii=False, indent=4)

def get_form_fields(infile):
    # infile = PdfReader(open(infile, 'rb'))
    fields = get_all_fields(infile)
    return fields


# Example usage
# pdf_template = "filled_data_groww.pdf"

pdf_template = "test_form_sample.pdf"

output_json_second = "fill_from_test.json"
# fields_second = read_all_form_fields(pdf_template)
# save_to_json(fields_second, output_json_second)

update_pdf_fields_from_json(
     pdf_path=pdf_template,
     output_path="filled_by_mupdf_test.pdf",
     fields_data=get_data_to_fill(file_name=output_json_second)
 )













