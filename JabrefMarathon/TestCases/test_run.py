#{{{ Marathon
from default import *
#}}} Marathon

def test():

    set_java_recorded_version("1.6.0_23")
    if window('JabRef'):
        click('fileopen')

        if window('Open'):
            select('JFileChooser_4', '/disk1/fitsum/home/workspace2/Jabref/test_run/bib1.bib')
        close()
        
        select_menu('Tools>>Consolidate a folder')
    close()

    if window('Consolidate Database'):
        click('Output Folder')

        if window('Open'):
            select('net.sf.jabref.JabRefFileChooser_4', '')
        close()

        click('Browse')

        if window('Open'):
            select('net.sf.jabref.JabRefFileChooser_4', '/disk1/fitsum/home/workspace2/Jabref')
        close()

        click('Output Folder')

        if window('Open'):
            select('net.sf.jabref.JabRefFileChooser_4', '#H/Desktop')
        close()

        click('Run')
    close()

    pass
