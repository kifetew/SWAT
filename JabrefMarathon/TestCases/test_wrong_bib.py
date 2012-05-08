#{{{ Marathon
from default import *
#}}} Marathon

def test():

    set_java_recorded_version("1.6.0_27")
    
    #if get_window() == 'JabRef':
    if window('JabRef'):
        click('fileopen')

        if window('Open'):
            select('JFileChooser_4', '/disk1/fitsum/home/workspace2/Jabref/bib1.bib')
        close()

        select('JTabbedPane_44', 'bib1.bib')

    if window('JabRef - /disk1/fitsum/home/workspace2/Jabref/bib1.bib'):
        select_menu('Tools>>Shrink a bib file')
    close()

    if window('Shrinking Database'):
        select('JTextField_6', 'nonexisiting')
        click('Browse_2')

        if window('Open'):
            select('net.sf.jabref.JabRefFileChooser_4', '/disk1/fitsum/home/workspace2/Jabref/testShrinking')
        close()

        click('Run')
        click('Close')
    close()


    pass